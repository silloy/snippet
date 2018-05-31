# -*- coding: utf-8 -*-
"""
Created on Mon Sep 14 17:04:59 2015

@author: ShaohuaSu
"""

import win32com.client as win32
import MySQLdb
app='Excel'
xl=win32.gencache.EnsureDispatch('%s'% app)

xlbook=win32.Dispatch('Excel.Application').Workbooks.Open('C:\\Users\\ShaohuaSu\\Desktop\\p.xlsx')
sh=xlbook.Worksheets('sheet1')
dfun=[]
jcount=2
ncount=1
#从第一列开始向下循环，找到为空的那一行返回行数，即为第ncount行为空
for ncount in range(1,65566):
    if sh.Cells(ncount,1).Value==None:
        break
    else:
        continue
#print ncount，测试代码，测试ncount值
#EXCEL的数据安排为第一行是字段，第二行开始是数据，故从第2行开始循环，将两列数据合并到一个LIST中
#LIST结构为[('a','b'),('c','d')]
for jcount in range(2,ncount):
    dfun.append((sh.Cells(jcount,1).Value,sh.Cells(jcount,2).Value))
#关闭EXCEL程序
#xl.Application.Quit()
#print dfun，测试代码测试dfun的数据
#建立一个空LIST fo用来存放字段，读取EXCEL中第一行的字段存储到fo中
fo=[]
icount=1
for icount in range(1,2):
    fo.append((sh.Cells(1,icount).Value,sh.Cells(1,icount+1).Value))
#测试代码，print fo
#打开MYSQL链接
conn=MySQLdb.connect(host='121.40.104.98',user='enginer',passwd='dataisbest',db='hmengine')
#获取游标操作
cursor=conn.cursor()
#建立一个空表
cursor.execute("create table test("+fo[0][0]+" varchar(100),"+fo[0][1]+" varchar(100));")
#利用executemany命令，将LIST，dfun中的数据通过insert语句写入数据库
cursor.executemany("""insert into test values(%s,%s);""" ,dfun)
#确认数据操作，注意如果没有conn.commit()这个命令，数据无法被插入到数据库中
conn.commit()


#执行查询检查结果
count = cursor.execute('select * from test') 
print 'has %s record' % count
#重置游标位置
cursor.scroll(0,mode='absolute')
#搜取所有结果
results = cursor.fetchall() 
#测试代码，print results
#获取MYSQL里的数据字段
fields = cursor.description
#将字段写入到EXCEL新表的第一行
sh2=xlbook.Worksheets('sheet3')
#清空sheet3
sh2.Cells.Clear
for ifs in range(1,len(fields)+1):
    sh2.Cells(1,ifs).Value=fields[ifs-1][0]
#将读取的数据填入到相应的行列中
ics=2
jcs=1
for ics in range(2,len(results)+2):
    for jcs in range(1,len(fields)+1):
        sh2.Cells(ics,jcs).Value=results[ics-2][jcs-1]

#关闭EXCEL程序
xl.Application.Quit()
#关闭游标和查询链接
cursor.close()
conn.close()

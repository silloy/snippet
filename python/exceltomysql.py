from openpyxl.reader.excel import load_workbook as lw
import mysql.connector as mc
import MySQLdb
import sys
reload (sys)
sys.getdefaultencoding()
sys.setdefaultencoding('utf8')

use=raw_input('user:')
pwd=raw_input('password:')
hos=raw_input('hostIP:')
db=raw_input('database:')
conn = mc.connect(user= use, password= pwd, host= hos, database= db)
cur = conn.cursor()
tab=raw_input('table:')

insert_sql = 'INSERT INTO `sym_b`  values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)'

wb = lw(filename = 'c:\\users\\ShaohuaSu\\Desktop\\p.xlsx')
ws = wb.get_sheet_by_name(wb.get_sheet_names()[0])    # <worksheet "data">

rows = ws.get_highest_row()         # 最大行数
columns = ws.get_highest_column()   # 最大列数
print rows
print columns

data = []
for rx in range(2, rows+1):
	for cx in range(1, columns+1):
		data.append(str(ws.cell(row=rx, column=cx).value))
	cur.execute(insert_sql, (data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9]))
	data = []

conn.commit()  # 提交
# 关闭两个连接
cur.close()    
conn.close()
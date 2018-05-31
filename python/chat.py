#coding=utf8
import itchat
from itchat.content import *
import urllib.parse
import urllib.request
import json
import time

msg_from = ''
friend = u'宝来'

#@itchat.msg_register([TEXT, PICTURE])
def simple_reply(msg):
	print(msg)
	if msg['Type'] == TEXT:
		return 'I received: %s' % msg['Content']

#@itchat.msg_register([TEXT, MAP, CARD, NOTE, SHARING,  PICTURE, RECORDING, ATTACHMENT, VIDEO, FRIENDS, SYSTEM])
def text_reply(msg):
	global msg_fromevernot
	msg_from = ''
	if itchat.search_friends(userName=msg['FromUserName'])['RemarkName'] == friend or \
	itchat.search_friends(userName=msg['FromUserName'])['NickName'] == friend:
		itchat.send(msg['Content'], toUserName = itchat.search_mps(name='小冰')[0]['UserName'])
		msg_from = msg_from + 'friend'

#@itchat.msg_register(TEXT, isGroupChat = True)
@itchat.msg_register([TEXT, MAP, CARD, NOTE, SHARING,  PICTURE, RECORDING, ATTACHMENT, VIDEO, FRIENDS, SYSTEM], isGroupChat = True)
def groupchat_reply(msg):
	global msg_from
	msg_from = ''
	if msg['FromUserName'] == itchat.search_chatrooms(name='不应该存在')[0]['UserName']:
		if msg['isAt']:
			mssg = msg['Content']
			itchat.send(mssg[mssg.find(' ') + 1:], toUserName = itchat.search_mps(name='小冰')[0]['UserName'])
		else:
			itchat.send(msg['Content'], toUserName = itchat.search_mps(name='小冰')[0]['UserName'])
			#itchat.send(u'@%s\u2005'  % (msg['ActualNickName']) + url_req(str[str.find(' ') + 1:]) + '😀', msg['FromUserName'])
	msg_from = msg_from + 'chatroom'
	print('Daky:', msg['Content'])

@itchat.msg_register([TEXT, PICTURE, RECORDING], isMpChat = True)
def mpchat_reply(msg):
	global msg_from
	value = {
		"key": "494a3cb3468a4c87bcfc8f64fe28400a",
		"info": msg["Content"],
		"loc":"北京市中关村",
		"userid":"123456"
	}
	if msg_from == u'chatroom':
		itchat.send(msg["Content"], itchat.search_chatrooms(name='不应该存在')[0]['UserName'])
	elif msg_from == u'friend':
		itchat.send(msg["Content"], itchat.search_friends(name=friend)[0]['UserName'])
	else:
		print('小冰: %s' %(msg['Content']))
		if msg['Type'] == u'TEXT':
			itchat.send('哟哟', msg['FromUserName'])
		else:
			tuling_msg = url_req(value)
			print('小图: %s' %(tuling_msg))
			itchat.send(tuling_msg, msg['FromUserName'])




# 发送消息给小图
def url_req(data):
	data = urllib.parse.urlencode(data).encode("utf-8")
	url = "http://www.tuling123.com/openapi/api"
	req = urllib.request.Request(url, data)
	response = urllib.request.urlopen(url = url,data = data)
	res = response.read().decode("utf8")
	res_data = json.loads(res)
	return res_data['text']

def tuling(msg, userId):
	userId = userId[2:30]
	data={"key":"", "info":msg, "userid":userId}
	headers = {"Content-Type": "application/json"}
	session = requests.Session()
	url = 'http://www.tuling123.com/openapi/api'
	r = session.post(url, data = json.dumps(data), headers=headers)
	result = eval(r.text)
	num = result['code']

	if num == 100000:
		return result['text']
	elif num == 200000:
		return result['text'] + ':\n' + result['url']
	else:
		return u'你说的我还听不懂'




# if itchat.load_login_status():
#     @itchat.msg_register(TEXT)
#     def simple_reply(msg):
#         print(msg['Text'])
#     itchat.run()
#     itchat.dump_login_status()
# else:
#     itchat.auto_login()
#     itchat.dump_login_status()
#     print('Config stored, so exit.')


# itchat.auto_login(hotReload=True, enableCmdQR=True)
itchat.auto_login(hotReload=True)
itchat.run()
itchat.dump_login_status()

# 获取自己的用户信息，返回自己的属性字典
# itchat.search_friends()
# 获取特定UserName的用户信息
#itchat.search_friends(userName='@abcdefg1234567')
# 获取任何一项等于name键值的用户
#itchat.search_friends(name='littlecodersh')
# 获取分别对应相应键值的用户
#itchat.search_friends(wechatAccount='littlecodersh')
# 三、四项功能可以一同使用
#itchat.search_friends(name='LittleCoder机器人', wechatAccount='littlecodersh')
#itchat.send(msg["Content"], itchat.search_chatrooms(name='果壳')[0]['UserName'])
#itchat.search_mps(name='小冰')[0]['UserName']

# # 收到好友邀请自动添加好友
# @itchat.msg_register(FRIENDS)
# def add_friend(msg):
#     itchat.add_friend(**msg['Text']) # 该操作会自动将新好友的消息录入，不需要重载通讯录
#     itchat.send_msg('Nice to meet you!', msg['RecommendInfo']['UserName'])

# # 以下四类的消息的Text键下存放了用于下载消息内容的方法，传入文件地址即可
# @itchat.msg_register([PICTURE, RECORDING, ATTACHMENT, VIDEO])
# def download_files(msg):
#     msg['Text'](msg['FileName'])
#     return '@%s@%s' % ({'Picture': 'img', 'Video': 'vid'}.get(msg['Type'], 'fil'), msg['FileName'])

# 下载方法接受一个可用的位置参数（包括文件名），并将文件相应的存储。
# @itchat.msg_register(['Picture', 'Recording', 'Attachment', 'Video'])
# def download_files(msg):
#     msg['Text'](msg['FileName'])
#     itchat.send('@%s@%s'%('img' if msg['Type'] == 'Picture' else 'fil', msg['FileName']), msg['FromUserName'])
#     return '%s received'%msg['Type']
	  # with open(msg['FileName'], 'wb') as f:
        # f.write(msg['Text']())
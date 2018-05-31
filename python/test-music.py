import urllib.parse
import urllib.request
import requests
import json
# value = {
# 		"key": "494a3cb3468a4c87bcfc8f64fe28400a",
# 		"info":"你好啊",
# 		"loc":"北京市中关村",
# 		"userid":"123456"
# }
# def url_req(data):
# 	data = urllib.parse.urlencode(data).encode("utf-8")
# 	url = "http://www.tuling123.com/openapi/api"
# 	req = urllib.request.Request(url, data)
# 	response = urllib.request.urlopen(url = url,data = data)
# 	res = response.read().decode("utf8")
# 	res_data = json.loads(res)
# 	print('%s: %s' % ('start: ', res_data['text']))
# 	data = {
# 		"key": "494a3cb3468a4c87bcfc8f64fe28400a",
# 		"info": res_data['text'],
# 		"loc":"北京市中关村",
# 		"userid":"123456"
# 		}
# 	return url_req(data)
# url_req(value);

my_cookie = {
    "version":0,
    "name":'appver',
    "value":'1.5.0.75771',
    "port":None,
    # "port_specified":False,
    "domain":'www.mydomain.com',
    # "domain_specified":False,
    # "domain_initial_dot":False,
    "path":'/',
    # "path_specified":True,
    "secure":False,
    "expires":None,
    "discard":True,
    "comment":None,
    "comment_url":None,
    "rest":{},
    "rfc2109":False
    }

api = 'http://music.163.com/api/album/34841457'
s = requests.Session()
s.headers.update({'Referer': "http://music.163.com/"})
s.cookies.set(**my_cookie)
response  = s.get(api)
json_data = json.loads(response.text)
print(response)
print(json_data)

data = 'hlpretag=<span class="s-fc7">&hlposttag=</span>&s=\http://music.163.com/api/search/pc\'种树\'&type=1&offset=1&total=true&limit=5'
#data = urllib.parse.urlencode(data).encode("utf-8")
data = data.encode("utf-8")
url = "http://music.163.com/api/search/get/web?csrf_token="
headers = { 'Host': 'music.163.com',
			'Origin': 'http://music.163.com',
			'User-Agent': 'Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36',
			'Content-Type': 'application/x-www-form-urlencoded',
			'Referer': 'http://music.163.com/search/'
		  }
req = urllib.request.Request(url, data)
req.add_header('Host', 'music.163.com')
req.add_header('Origin', 'http://music.163.com')
req.add_header('User-Agent', 'Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36')
req.add_header('Content-Type', 'application/x-www-form-urlencoded')
req.add_header('Referer', 'http://music.163.com/search/')
response = urllib.request.urlopen(url = url,data = data)
res = response.read().decode("utf8")
res_data = json.loads(res)
print('%s: %s' % ('start: ', res_data))

import hashlib
import json
import time
def md5(s):
    m = hashlib.md5(s.encode('utf8'))
    return m.hexdigest()

    # keys = list(parameters.keys())

appkey = '58e895dac8957634c000190b'
app_master_secret = '0308a6eac0143413c5844a4e12a9c899'
timestamp = time.localtime(time.time())
timestamp = time.mktime(timestamp)
print(int(timestamp))
method = 'POST'
url = 'http://msg.umeng.com/api/send'
params = {'appkey': appkey,
          'timestamp': timestamp,
          'device_tokens': 'AmXNhF-gY20ljNr8LfG0yoCZoMlfION6-mdYYmzVjj48',
          'type': 'unicast',
          'payload': {'body': {'ticker': 'Hello World',
                               'title':'你好',
                               'text':'来自友盟推送',
                               'after_open': 'go_app'},
                      'display_type': 'notification'
          }
}
post_body = json.dumps(params)
print(post_body)
sign = md5('%s%s%s%s' % (method,url,post_body,app_master_secret))
print(sign)
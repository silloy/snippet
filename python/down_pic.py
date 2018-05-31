#!python3
#coding=utf-8

import requests
import bs4
import os

header = {'User-Agent':
                         'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36'
                        '(KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36'
         }

# 请求网址
def req(start_page, end_page):
    fetched_pic_add_all = []
    for i in range(start_page, end_page):
        simple_url = 'http://jandan.net/ooxx/page-' + str(i)
        try:
            response = requests.get(simple_url, headers=header)
        except Exception as e:
            print('ERROR')
        else:
            content = response.text
            fetched_info = bs4.BeautifulSoup(content, 'html.parser')
            fetched_info_tagA = fetched_info.find_all('a', 'view_img_link')
            fetched_pic_add = [l.get('href') for l in fetched_info_tagA]
            for i in fetched_pic_add:
                fetched_pic_add_all.append(i)

    return fetched_pic_add_all

def download_pic(pic_addresses):
    for i in pic_addresses:
        try:
            i = 'http:' + i
            pic_resp = requests.get(i, headers=header)
            pic = pic_resp.content
            pic_name = i.split('/')[-1]
            with open('pic/' + pic_name, 'wb') as f:
                f.write(pic)
                print('pic\t' + pic_name + '\t downloaded')
        except Exception as e:
            print('ERR')

def main():
    create_file = os.path.exists('pic')
    if not create_file:
        os.mkdir('pic')
        print('create Folder')

    print('Set up start_page and end_page:')
    while True:
        try:
            start_page = int(input('Start_page: '))
            end_page = int(input('End_page: '))
            if end_page < start_page:
                print('Error page set')
                continue
        except Exception as e:
            print('ERROR input')
        else:
            break
    print('Downloading.....')
    pic_addresses = req(start_page, end_page)
    download_pic(pic_addresses)
    print('Download end')

if __name__ == '__main__':
    main()


#!/usr/bin/python
import sys
import urllib
import urllib2
import json 

reload(sys)
sys.setdefaultencoding('utf-8')

if __name__=="__main__":
    reload(sys)
    sys.setdefaultencoding('utf-8')
    if len(sys.argv) == 3:
        file_name = sys.argv[1]
        reference = sys.argv[2]
    else:
        print "Wrong command: python to_csv.py [TXT_FILE] [reference]"
        
    file = open(file_name, 'r')
    result =[] 
    curDiseaseName = ''
    curKeyName = ''
    curKeyNo = 0 
    isNewRecord = False
    fields = {} 
    for line in file.readlines():
		line = line.strip()

		bpos = line.find("<d>")
		epos = line.find("</d>")
		if bpos >= 0 and epos > 0:
			curDiseaseName = line[bpos + 3 : epos] 
			curKeyNo = 0
			isNewRecord = True
			continue

		bpos = line.find("<k>")
		epos = line.find("</k>") 
		if bpos >= 0 and epos > 0: 
			if len(curKeyName) > 0:
				result.append(fields)
			curKeyName = line[bpos + 3 : epos]
			curKeyNo = curKeyNo + 1
			isNewRecord = True
			fields = {} 
			continue

		if isNewRecord:
			fields["disease_name"] = curDiseaseName 
			fields["key_name"] = curKeyName
			fields["key_id"] = curKeyNo 
			fields["detail"] = line + "<br>" 
			isNewRecord = False
		else:
			fields["detail"] += line + "<br>" 
    result.append(fields)
    print "%s\t%s\t%s\t%s\t%s" % ("disease_name","key_name","key_id","detail","reference")
    for record in result:
        print "%s\t%s\t%s\t%s\t%s" % (record["disease_name"],record["key_name"],record["key_id"],record["detail"], reference)

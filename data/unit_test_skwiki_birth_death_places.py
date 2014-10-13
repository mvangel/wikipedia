 #!/usr/bin/python3 

 # Copyright (C) 2013 Timotej Tkac.


#python3 unit_test_skwiki_birth_death_places.py 
#Expected output:
#Steve Jobs;24.2.1955;5.10.2011;Spojené štáty|San Francisco|Kalifornia|USA|Palo Alto
#Parsed output: 
#Steve Jobs;24.2.1955;5.10.2011;Spojené štáty|San Francisco|Kalifornia|USA|Palo Alto
#Parsing successfull

import xml.etree.ElementTree as etree
import io
import struct
import re

def check_path(value):
    svalue = value.strip()
    if not os.path.isfile(svalue):
         raise ArgumentTypeError("%s is an invalid file path" % svalue)
    return svalue


if __name__ == '__main__':
        import sys
        try:
            from argparse import ArgumentParser,ArgumentTypeError
            import string	
            import datetime
            import os
            import socket
            parser = ArgumentParser()
            parser.add_argument("-f", "--file", dest="input_filename",
                               help="read message from FILE", metavar="FILE", type=check_path, default="sample_input_infobox_skwiki-latest-pages-articles.xml")

            args = parser.parse_args()
            entry=''
            title=''
            htmlcomments = re.compile('\<![ \r\n\t]*(--([^\-]|[\r\n]|-[^\-])*--[ \r\n\t]*)\>')
            dateb1 = re.compile('\{\{[\w ]+\|[0-9]*\|[0-9]+\|[0-9]*\}\}')
            #{{dúv|1971|11|14|1914|2|12}}
            dated1 = re.compile('\{\{[\w ]+\|[0-9]*\|[0-9]+\|[0-9]*\|[0-9]*\|[0-9]*\|[0-9]*\}\}')
            #[[935]]
            date2 = re.compile('\[\[[0-9]+\]\]')
            date3 = re.compile('[0-9]+$')
            date4 = re.compile('\[\[[0-9]+. storočie\]\]')
            print("Expected output:")
            correct="Steve Jobs;24.2.1955;5.10.2011;Spojené štáty|San Francisco|Kalifornia|USA|Palo Alto"
            print(correct)
            print("Parsed output: ")
          #  place = re.compile('[A-Z]+')
            for event, elem in etree.iterparse(open(args.input_filename, "rb"), events=('start', 'end')):
              
              if event == 'start':
                    pass
              if event == 'end':
                
                if 'title' in elem.tag:
                  title=elem.text.strip()
                if 'text' in elem.tag and elem.text and 'Infobox' not in title:
                  birth=''
                  death=''
                  name=''
                  places=set()
                  for line in elem.text.split('\n'):

 #       month_numbers = {'-Jan-': '-01-', '-Feb-': '-02-', ...}
#for k, v in month_numbers.items():
    #line = line.replace(k, v)
                    stripped=' '.join(line.strip(' |\t\n\r').split())
                    if "meno=" in stripped.lower().replace(" ","").lower() and not name:
                       name=htmlcomments.sub('', stripped.split('=')[1]).strip(" '\r\n\t").replace('{{PAGENAME}}',title).replace('?','').replace('<center>','').split('<')[0].strip(' \r\n\t[]')
                    elif not birth and ("dátum" in  stripped.lower() and "narodenia=" in  stripped.lower().replace(" ","")) or "narodenie=" in stripped.lower().replace(" ",""):
                        value=stripped.split('=')[1].replace(' r.','').replace('okolo','').replace('po','').replace('asi','').replace('cca','').split('<')[0].strip()
                        if value:
                          var=htmlcomments.sub('', value).strip().replace('?','')
                          if dateb1.search(var):
                            birth=dateb1.search(var).group(0).strip('{}').split('|')[1:4]
                            birth[0]="{0:04d}".format(int(birth[0]))
                            try:
                               struct_time = datetime.datetime.strptime(' '.join(birth), "%Y %m %d")
                            except:
                               struct_time = datetime.datetime.strptime(birth[0], "%Y")
                            birth=(str(struct_time.day) +'.'+ str(struct_time.month) +'.'+ str(struct_time.year))
                          elif date2.search(var):
                            birth=date2.search(var).group(0).strip('[]')
                            struct_time = datetime.datetime.strptime("{0:04d}".format(int(birth)), "%Y")
                            birth=(str(struct_time.day) +'.'+ str(struct_time.month) +'.'+ str(struct_time.year))
                          elif date3.match(var):
                            struct_time = datetime.datetime.strptime("{0:04d}".format(int(date3.match(var).group(0))), "%Y")
                            birth=(str(struct_time.day) +'.'+ str(struct_time.month) +'.'+ str(struct_time.year))
                          elif date4.match(var):
                            struct_time = datetime.datetime.strptime("{0:04d}".format(int(date4.match(var).group(0).split('.')[0].strip('[]'))*100), "%Y")
                            birth=(str(struct_time.day) +'.'+ str(struct_time.month) +'.'+ str(struct_time.year))
                          elif var:
                             birth='??' +var
                    elif ("dátum" in stripped.lower() and "úmrtia=" in  stripped.lower().replace(" ","")) or "úmrtie=" in stripped.lower().replace(" ",""):
                        value=stripped.split('=')[1].replace(' r.','').replace('okolo','').replace('po','').replace('asi','').replace('cca','').split('<')[0].strip()
                        if value != '':
                          var=htmlcomments.sub('', value).strip().replace('?','')
                          if dated1.search(var):
                            death=dated1.search(var).group(0).split('|')[1:4]
                            death[0]="{0:04d}".format(int(death[0]))
                    
                            death=' '.join(death)
                            struct_time = datetime.datetime.strptime(death, "%Y %m %d")
                            death=(str(struct_time.day) +'.'+ str(struct_time.month) +'.'+ str(struct_time.year))
                            birth=dated1.search(var).group(0).strip('{}').split('|')[4:7]
                            birth[0]="{0:04d}".format(int(birth[0]))
                            try:
                               struct_time = datetime.datetime.strptime(' '.join(birth), "%Y %m %d")
                            except:
                              struct_time = datetime.datetime.strptime(birth[0], "%Y")
                            birth=(str(struct_time.day) +'.'+ str(struct_time.month) +'.'+ str(struct_time.year))
                          elif date2.search(var):
                            death=date2.search(var).group(0).strip('[]')
                            struct_time = datetime.datetime.strptime("{0:04d}".format(int(death)), "%Y")
                            death=(str(struct_time.day) +'.'+ str(struct_time.month) +'.'+ str(struct_time.year))
                          elif date3.match(var):
                            struct_time= datetime.datetime.strptime("{0:04d}".format(int(date3.match(var).group(0))), "%Y")
                            death=(str(struct_time.day) +'.'+ str(struct_time.month) +'.'+ str(struct_time.year))
                          elif date4.match(var):
                            struct_time = datetime.datetime.strptime("{0:04d}".format(int(date4.match(var).group(0).split('.')[0].strip('[]'))*100), "%Y")
                            death=(str(struct_time.day) +'.'+ str(struct_time.month) +'.'+ str(struct_time.year))
                          elif var:
                           # entry+=";"+ datetime.strptime(value, '%b %d %Y %I:%M%p')
                        
                             death='??' +var
                    elif stripped.startswith('}}'):
                      break
                    if "miesto" in stripped.lower().split('=')[0] and '=' in stripped:
                       #print(stripped)
                          word= stripped.split('=')[1]
                       	  #print(word)
                       	  chars = ['{','}','[',']','|','(',')',';']
                       	  for char in chars:
                       	  	if char in word:
                       	  		word=word.replace(char,';')
                       	  for place in word.split(';'):
                       	     if place and place[0].isupper():
                       	         places.add(place.strip())                       	       
                  if birth or death:
                     if not name:
                        name=title
                     out=(name + ';' + birth + ';' + death + ';' + '|'.join(list(places)))
                     print(out)
                     if out == correct:
                       print("Parsing successfull")
                     else:
                      print("Parsing failed")
                elem.clear()   
        except socket.error:
            pass

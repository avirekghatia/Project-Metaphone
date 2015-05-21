#! /usr/bin/env python
import os.path
import urllib2
import re
import time
from bs4 import BeautifulSoup
string = 'http://www.bollywoodlyrics.com/lyric/'
need ='http://www.bollywoodlyrics.com/lyric/'
remove1 = '#respond'
remove2 = '#comment'
remove3 = 'page'
list_empty = True
result = []
substring = '/lyric/'
startingurl = 'http://www.bollywoodlyrics.com/music_composer'
visited = [startingurl]
fish_url = startingurl
#page = urllib2.urlopen('http://www.google.com')
req = urllib2.Request(startingurl, headers={'User-Agent' : "Magic Browser"})
page = urllib2.urlopen( req )
#page = urllib2.urlopen(fish_url)
print("i read")
html_doc = page.read()
soup = BeautifulSoup(html_doc)
i=0
for fish in soup.findAll('a', href=True):
        if startingurl in fish['href'] or need in fish['href']:
                if remove1 in fish['href']:
                        pass
                elif remove2 in fish['href']:
                        pass
                elif fish['href'] == startingurl:
                        pass
                else:
                        try:
                                result.index(fish['href'])
                        except ValueError: 
                                result.append(fish['href']) 
                               # print(fish['href']) 
       
       
       
while len(result):
         string = result[i]
        # print 'visiting  ' + string
         try:
         #    print 'm here'
             visited.index(string)
             result.remove(string)
          #   print 'm in visit list1'
         except ValueError:
           #     print 'm not yet visited'
                fish_url = string
                try:
                        result.remove(string)
                        visited.append(string)
                        req = urllib2.Request(fish_url, headers={'User-Agent' : "Magic Browser"})
                        page = urllib2.urlopen( req )
                        #page = urllib2.urlopen(fish_url)    
                        html_doc = page.read()
                        soup = BeautifulSoup(html_doc)
                        for fish in soup.findAll('a', href=True):
            #                    print fish['href']
                                
                                if startingurl in fish['href'] or need in fish['href']:
                                        if remove1 in fish['href'] or remove2 in fish['href'] or fish['href'] == startingurl:
                                                pass
                                        else:
             #                                   print(fish['href'])
                                                try:
                                                        visited.index(fish['href'])
              #                                          print 'm in visit list2'
                                                except ValueError:
                                                         try:
                                                                result.index(fish['href'])
               #                                                 print 'm in result list'
                                                         except ValueError:
                                                                if need in fish['href']:
                #                                                        print 'to write ' + fish['href']  
                                                                        if remove1 in fish['href'] or remove2 in fish['href'] or remove3 in fish['href'] or need == fish['href'] :
                                                                                pass
                                                                        else:
                                                                                val = fish['href'].index(substring)
                                                                                val = val + 1
                                                                                sub = fish['href'][val:]
                                                                                if not os.path.exists(sub):
                                                                                        try:
                                                                                                req = urllib2.Request(fish['href'], headers={'User-Agent' : "Magic Browser"})
                                                                                                page1 = urllib2.urlopen( req )
                                                                                                #page1 = urllib2.urlopen(fish['href'])
                                                                                                visited.append(fish['href'])         
                                                                        
                                                                                                html_doc1 = page1.read()
                                                                                                soup1 = BeautifulSoup(html_doc1)
                                                                                                f = open(sub,'w')
                                                                                                text = soup1.get_text().encode('utf8')
                                                                                                index1 = text.index('BEGIN .entry-meta .entry-header')
                                                                                               # print index1
                                                                                                index2 = text.index('END .entry-content ')
                                                                                                #print index2
                                                                                                dist = index2 - index1
                                                                                                #print dist
                                                                                                text2 = text[index1:index2]
                                                                                                #print text2
                                                                                                f.write(text2)
                                                                                                f.close
                                                                                                print('file written')
                                                                                        except  urllib2.HTTPError, e:
                                                                                                result.append(fish['href']) 
                                                                                                #print 'appending1' 
                                                                                        except urllib2.URLError:
                                                                                                result.append(fish['href']) 
                                                                                                #print 'appending2'
                                                                                else:
                                                                                        print 'file exist'        
                                                                                                
                                                                else:      
                                                                        result.append(fish['href']) 
                                                                        #print 'appending' 
                
                
                
                        if need in string:
                                #print 'to write ' + string  
                                if remove1 in string or remove2 in string or remove3 in string or need==  string:
                                        pass
                                else:
                                        val = string.index(substring)
                                        val = val + 1
                                        sub = string[val:]
                                        if not os.path.exists(sub):
                                                try:
                                                        f = open(sub,'w')
                                                        text = soup.get_text().encode('utf8')
                                                        index1 = text.index('BEGIN .entry-meta .entry-header')
                                                        #print index1
                                                        index2 = text.index('END .entry-content ')
                                                        #print index2
                                                        dist = index2 - index1
                                                        #print dist
                                                        text2 = text[index1:index2]
                                                        #print text2
                                                        f.write(text2)
                                                        f.close
                                                        print(' i am writing')
                                                except  urllib2.HTTPError, e:
                                                        pass
                                                except urllib2.URLError:
                                                        pass 
                                        else:
                                                print 'file exist'             
                
                except urllib2.HTTPError, e:
                        result.append(string)
                        visited.remove(string)
                        #print 'can not open'
                except urllib2.URLError:
                        result.append(string)
                        visited.remove(string)
                        #print 'can not open'
                         
                        
                        

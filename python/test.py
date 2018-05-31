#! python3
# coding: utf-8
import sys
import pprint
import copy
import csv
import math
from collections import Iterable
import os
from functools import reduce
import functools
from enum import Enum, unique
import os
import re


#name = input('please enter your name: ')
#print('hello,', name)

print('The quick brown fox', 'jumps over', 'the lazy dog')
#pprint.pprint(sys.path)

def hello():
    print("world")

hello()
print(sys.getdefaultencoding())
print(u"中文")
print(sys.stdout.encoding)
print(sys.stderr.encoding)
if __name__ == '__main__': hello()
print(copy.__all__)
print(range.__doc__)
print(help(copy.copy))
print(csv.__file__)
print(1/7)
print(None)
a = 'ABC'
b = a
a = 'XYZ'
print(b)

n = 123
f = 456.789
s1 = 'Hello, world'
s2 = 'Hello, \'Adam\''
s3 = r'Hello, "Bart"'
s4 = r'''Hello,
Lisa!'''
print(n, f, s1, s2, s3, s4)

L = [
    ['Apple', 'Google', 'Microsoft'],
    ['Java', 'Python', 'Ruby', 'PHP'],
    ['Adam', 'Bart', 'Lisa']
]

# 打印Apple:
print(L[0][0])
# 打印Python:
print(L[1][1])
# 打印Lisa:
print(L[2][2])

age = 2
if age >= 18:
    print('your age is', age)
    print('adult')

names = ['Michael', 'Bob', 'Tracy']
for name in names:
    print(name)

sum = 0
for x in range(101):
    sum += x
print(sum)

d = {'Michael': 95, 'Bob': 75, 'Tracy': 85}
print(d['Michael'])
print("Michael" in d)
print(d.get("Michael", -1))
b = abs(-100)
print(b)
n1 = 255
n2 = 1000
print(hex(n1))
def nop():
    pass

def move(x, y, step, angle=0):
    nx = x + step * math.cos(angle)
    ny = y - step * math.sin(angle)
    return nx, ny

x, y = move(100, 100, 60, math.pi / 6)
print(x, y)

r = move(100, 100, 60, math.pi / 6)
print(r)

print(math.pow(5, 3))

def calc(numbers):
    sum = 0
    for n in numbers:
        sum = sum + n * n
    return sum

print(calc([1, 2, 3]))
#可变长参数
def calcs(*numbers):
    sum = 0
    for n in numbers:
        sum = sum + n * n
    return sum
print(calcs(1, 2, 3))

nums = [1, 2, 3]

#print(calc(*nums))

def fact(n):
    if n==1:
        return 1
    return n * fact(n - 1)
print(fact(5))

def factx(n):
    return fact_iter(n, 1)

def fact_iter(num, product):
    if num == 1:
        return product
    return fact_iter(num - 1, num * product)

print(factx(17))
for ch in 'ABC':
    print(ch)

print(isinstance('abc', Iterable)) #是否可循环判断
print([x * x for x in range(1, 11)])

list2 =  [x * x for x in range(1, 11) if x % 2 == 0]
print(list2)
double = [m + n + l for m in 'ABC' for n in 'XYZ' for l in 'DEF']
print(double)
print([d for d in os.listdir('.')])

d = {'x': 'A', 'y': 'B', 'z': 'C' }
for k, v in d.items():
    print(k, '=', v)

def fib(max):
    n, a, b = 0, 0, 1
    while n < max:
        yield b
        a, b = b, a + b
        n = n + 1
    return 'done'
print(fib(6))

def f(x):
    return x * x

r = map(f, [1, 2, 3, 4, 5, 6])
print(list(r))

def fn(x, y):
    return x * 10 + y

print(reduce(fn, [1, 3, 5, 9 ,4]))

# 利用map和reduce编写一个str2float函数，把字符串'123.456'转换成浮点数123.456

def normalize(name):
    return (name.lower()).capitalize()

L1 = ['adam', 'LISA', 'barT']
L2 = list(map(normalize, L1))
print(L2)

def _odd_iter():
    n = 1
    while True:
        n = n + 2
        yield n
def _not_divisible(n):
    return lambda x: x % n > 0

def primes():
    yield 2
    it = _odd_iter() # 初始序列
    while True:
        n = next(it) # 返回序列的第一个数
        yield n
        it = filter(_not_divisible(n), it) # 构造新序列

for n in primes():
    if n < 100:
        print(n)
    else:
        break

f = lambda x: x * x
print(f)
print(f(5))
print(f.__name__)

def log(func):
    def wrapper(*args, **kw):
        print('call %s():' % func.__name__)
        return func(*args, **kw)
    return wrapper
@log
def now():
    print('2015-3-25')

now()

def log(text):
    def decorator(func):
        @functools.wraps(func)
        def wrapper(*args, **kw):
            print('%s %s():' % (text, func.__name__))
            return func(*args, **kw)
        return wrapper
    return decorator

@log('execute')
def nnow():
    print('2015-3-25')

nnow();

number = int('123457', base=8)
print(number)

def int2(x, base=2):
    return int(x, base)
int22 = functools.partial(int, base=2)

def test1():
    args = sys.argv
    if len(args)==1:
            print('Hello, world!')
    elif len(args)==2:
        print('Hello, %s!' % args[1])
    else:
        print('Too many arguments!')

print(sys.path)
#sys.path.append()
print(type(123))

class Student(object):

    @property
    def score(self):
        return self._score

    @score.setter
    def score(self, value):
        if not isinstance(value, int):
            raise ValueError('score must be an integer!')
        if value < 0 or value > 100:
            raise ValueError('score must between 0 ~ 100!')
        self._score = value

s = Student()
s.score=60
print(s.score)

class Fib(object):
    def __init__(self):
        self.a, self.b = 0, 1 # 初始化两个计数器a，b

    def __iter__(self):
        return self # 实例本身就是迭代对象，故返回自己

    def __next__(self):
        self.a, self.b = self.b, self.a + self.b # 计算下一个值
        if self.a > 100000: # 退出循环的条件
            raise StopIteration();
        return self.a # 返回下一个值
    def __getitem__(self, n):
        if isinstance(n, int): # n是索引
            a, b = 1, 1
            for x in range(n):
                a, b = b, a + b
            return a
        if isinstance(n, slice): # n是切片
            start = n.start
            stop = n.stop
            if start is None:
                start = 0
            a, b = 1, 1
            L = []
            for x in range(stop):
                if x >= start:
                    L.append(a)
                a, b = b, a + b
            return L

for n in Fib():
    print(n)
f = Fib()
print(f[87])
print(f[0:6])
Month = Enum('Month', ('Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'))

for name, member in Month.__members__.items():
    print(name, '=>', member, ',', member.value)

@unique
class Weekday(Enum):
    Sun = 0 # Sun的value被设定为0
    Mon = 1
    Tue = 2
    Wed = 3
    Thu = 4
    Fri = 5
    Sat = 6

for name, member in Weekday.__members__.items():
    print(name, '=>', member, ',', member.value)

print(os.name)
#print(os.environ)
print(os.path.abspath('.'))
telphone = re.compile(r'^(\d{3})-(\d{3,8})$')
print(telphone)

print('Test: 010-12345')
m = re.match(r'^(\d{3})-(\d{3,8})$', '010-12345')
print(m.group(1), m.group(2))

t = '19:05:30'
print('Test:', t)
m = re.match(r'^(0[0-9]|1[0-9]|2[0-3]|[0-9])\:(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|[0-9])\:(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|[0-9])$', t)
print(m.groups())

print('start')

def echo_bar(self):
    print (self.bar)
Foo = type('Foo', (), {'bar':False})
FooChild = type('FooChild', (Foo,), {'echo_bar': echo_bar})
print(hasattr(Foo, 'echo_bar'))
print(hasattr(FooChild, 'echo_bar'))

my_foo = FooChild()
my_foo.echo_bar()

print(True)

map = {}.fromkeys(['name', 'age'])
map['name'] = 'Smith'
print(map)
print (map.get('dfd', 'N/A'))

#labels = {'phone':'phone number', 'addr':'address'}
#name = input('Name: ')
#request = input('Phone (p) or address (a)? ')
#key = request
#if request == 'p':
#    key = 'phone'
#if request == 'a':
#    key = 'addr'

#person = map.get(name, 'John')
#label = labels.get(key, key)
#result = labels.get(key, 'not available')

#print ("%s's %s is %s. " %(name, label, result))

print(1,2,3)

values = 1,2,3
print(values)

storage = {}
storage['first'] = {}
storage['middle'] = {}
storage['last'] = {}
me  = 'Magnus Lie Hetland'
storage['first']['Magnus'] = [me]
storage['middle']['Lie'] = [me]
storage['last']['Hetland'] = [me]
print(storage['middle']['Lie'])

def zoo(x, y, z, m=0, n=0):
    print(x, y, z, m, n)

def call_zoo(*args, **kwds):
    print("Calling zoo: ")
    zoo(*args, **kwds)

args = (3,4,5)
kwds = {'m':8, 'n':7} 
call_zoo(*args, **kwds)


import this
s=this.s
d = {}
for c in (65, 97):
    for i in range(26):
        d[chr(i+c)] = chr((i+13) % 26 + c)

print ('python 之禅')
print ("".join([d.get(c, c) for c in s]))

class Filter:
    """docstring for Filter"""
    def init(self, arg):
        self.blocked = []
    def filter(self, sequence):
        return [x for x in sequence if x not in self.blocked]

class SPAMFilter(Filter):
    """docstring for SPAMFilter"""
    def init(self):
        self.blocked = ['SPAM']

s = SPAMFilter()
s.init()
s.filter(['SPAM', 'SPAM', 'eggs', 'bacon'])

print(SPAMFilter.__bases__)

class CountList(list):
    """docstring for CountList"""
    def __init__(self, *args):
        super(CountList, self).__init__(*args)
        self.count = 0
    def __getitem__(self, index):
        self.count += 1
        return super(CountList, self).__getitem__(index)

c1 = CountList(range(10))
print(c1[4] + c1[3])
print(c1.count)

__metaclass__ = type
class Rectangle(object):
    """docstring for Rectangle"""
    def __init__(self):
        self.width = 0
        self.height = 0
    def setSize(self, size):
        self.width, self.height = size
    def getSize(self):
        return self.width, self.height
    size = property(getSize, setSize)

def faltten(nested):
    try:
        for sublist in nested:
            for element in faltten(sublist):
                yield element
    except TypeError:
        yield nested

print(list(faltten([2,4,5, [1,2, [3, [10, 11, [12], [13,14], [15,[16,17,[18]]]]]]])))

# 八皇后问题
def conflict(state, nextX):
    nextY = len(state)
    for i in range(nextY):
        if abs(state[i] - nextX) in (0, nextY - i):
            return True
    return False

def queens(num=8, state=()):
    for pos in range(num):
        if not conflict(state, pos):
            if len(state) == num-1:
                yield(pos,)
            else:
                for result in queens(num, state + (pos,)):
                    yield (pos,) + result

#print(list(queens(8)))


import random
def prettyprint(solution):
    def line(pos, length=len(solution)):
        return '. ' * (pos) + 'X ' + '. '*(length-pos-1)
    for pos in solution:
        print(line(pos))

if __name__ == "__main__":
    prettyprint(random.choice(list(queens(8))))

import heapq

heap = [5, 6, 0, 3, 6, 7, 9, 1, 4, 2]
heapq.heapify(heap)
print(heap)
heapq.heapreplace(heap, 10)
print(heap)

from collections import deque
Q = deque(range(5))

import time
print(time.asctime())
print(time.time())

import re
pat = '[a-zA-Z]+'
text = '"Hm... err -- are you sure?" he said, sounding insecure.'
print(re.findall(pat, text))
pat = r'[.?\-","]+'
print(re.findall(pat, text))

MAT = re.match(r'www\.(.*)\..{3}', 'www.python.org')
m.group(1)
m.start(1)
m.end(1)
m.span(1)

emphsis_pattern = r'\*([^\*]+)\*'
re.sub(emphsis_pattern, r'<em>\1</em>', 'Hello, *world*')
print(re.sub(emphsis_pattern, r'<em>\1</em>', 'Hello, *world*'))

# templates.py

# import fileinput, re
# field_pat = re.compile(r'\[(.+?)\]')
# scope = {}
# def replacement(match):
#     code = match.group(1)
#     try:
#         return str(eval(code, scope))
#     except SyntaxError:
#         exec(code in scope)
#         return ''
# lines = []
# for line in fileinput.input():
#     lines.append(line)
# text.join(lines)
# print(field_pat.sub(replacement, text))
#from __future__ import with_statement


strings = '@1234 dfsd'
print(strings[strings.find(' ') + 1:])

pattern = re.compile(r'Hello', re.L)

matchs = pattern.match('Hello world')

if matchs:
    print(matchs.group())

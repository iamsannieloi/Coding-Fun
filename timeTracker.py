#!usr/bin/python

from Tkinter import *
from plyer import notification
from os.path import dirname
from os.path import join
from os.path import realpath
import datetime
import time

master = Tk()
master.wm_title("Sannie Time Tracker")
#code to add widgets will go here ...

topFrame = Frame(master)
topFrame.pack(side = TOP)
middleFrame = Frame(topFrame)
middleFrame.pack(side = BOTTOM)
bottomFrame = Frame(master)
bottomFrame.pack(side = BOTTOM)
doneFrame = Frame(bottomFrame)
doneFrame.pack(side = BOTTOM)


taskStarted = 0
idleTime = int(time.time())

def checkIdle():
    global idleTime
    global taskStarted
    curTime = int(time.time())
    if ( taskStarted == 0 and curTime - idleTime > 30 ):
        print curTime
	sendNotification()
    master.after(30000, checkIdle)

def sendNotification():
    notification.notify(title='Current Task', message="No Tasks being worked on!!", app_name="TimeTracker9000", app_icon=join(dirname(realpath(__file__)),"Haro-icon.gif")) 

def getCurrentTime():
    currentTime = datetime.datetime.now().replace(microsecond=0)
    return currentTime

def startTask():
    global userTask
    global startTime
    global taskInfo
    global trial
    userTask = task.get()
    startTime = getCurrentTime()
    taskInfo = str(startTime.strftime('%Y-%m-%d')) + "\t" + str(startTime.strftime('%H:%M:%S')) 
    trial = Label(middleFrame, text=taskInfo+"\t"+userTask)
    trial.pack(side=LEFT)
    global taskStarted
    taskStarted = 1

def doneTask():
    doneTime = getCurrentTime()
    global taskStarted
    if (taskStarted):
	    totalTime = doneTime - startTime
	    recordTask = taskInfo + "-" + str(doneTime.strftime('%H:%M:%S')) + "\t"+ userTask + "\t" + str(totalTime)
	    summary.insert(INSERT, recordTask + '\n')
	    trial.destroy()
	    taskStarted = 0
	    global idleTime
	    idleTime = int(time.time())

def makeentry(parent, caption, width=None, **options):
    Label(parent, text=caption).pack(side=LEFT)
    entry = Entry(parent, **options)
    if width:
        entry.config(width=width)
    entry.pack(side=LEFT)
    return entry

def retrieve_input():
    inputs = summary.get("1.0",'end-1c')
    print inputs


task = makeentry(topFrame, "Task", 50)

startButton = Button(topFrame, text="Start", width=10, command=startTask)
startButton.pack(side=RIGHT)

summary = Text(bottomFrame, bd=5, height=10)
summary.pack(side=LEFT)

endButton = Button(middleFrame, text="Done", width=10, command=doneTask)
endButton.pack(side=RIGHT)

jiraIssue = Text(bottomFrame, bd=2, height=10, width=10)
jiraIssue.pack(side=RIGHT)


submitButton = Button(doneFrame, text="Submit", width=10, command=retrieve_input)
submitButton.pack(side=RIGHT)

master.after(30000, checkIdle)
mainloop()



#user = makeentry(topFrame, "User name:", 10)
#password = makeentry(topFrame, "Password:", 10, show="*")

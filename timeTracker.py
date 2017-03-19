#!usr/bin/python

from Tkinter import *
import datetime

master = Tk()
#code to add widgets will go here ...

topFrame = Frame(master)
topFrame.pack(side = TOP)
middleFrame = Frame(topFrame)
middleFrame.pack(side = BOTTOM)
bottomFrame = Frame(master)
bottomFrame.pack(side = BOTTOM)
doneFrame = Frame(bottomFrame)
doneFrame.pack(side = BOTTOM)


def getCurrentTime():
    currentTime = datetime.datetime.now().replace(microsecond=0)
    return currentTime

def startTask():
    userTask = task.get()
    global startTime
    startTime = getCurrentTime()
    global taskInfo
    taskInfo = userTask + "\t" + str(startTime.strftime('%Y-%m-%d')) + "\t" + str(startTime.strftime('%H:%M:%S')) 
    global trial
    trial = Label(middleFrame, text=taskInfo)
    trial.pack(side=LEFT)

def doneTask():
    doneTime = getCurrentTime()
    totalTime = doneTime - startTime
    recordTask = taskInfo + "-" + str(doneTime.strftime('%H:%M:%S')) + "\t" + str(totalTime)
    summary.insert(INSERT, recordTask + '\n')
    trial.destroy()

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

#This displays the tasks currently logged
summary = Text(bottomFrame, bd=5, height=10)
summary.pack(side=LEFT)

endButton = Button(middleFrame, text="Done", width=10, command=doneTask)
endButton.pack(side=RIGHT)

jiraIssue = Text(bottomFrame, bd=2, height=10, width=10)
jiraIssue.pack(side=RIGHT)


submitButton = Button(doneFrame, text="Submit", width=10, command=retrieve_input)
submitButton.pack(side=RIGHT)
mainloop()



#user = makeentry(topFrame, "User name:", 10)
#password = makeentry(topFrame, "Password:", 10, show="*")

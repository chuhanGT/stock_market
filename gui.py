### GUI for stock price
### by Chu Han

from Tkinter import *
import tkMessageBox
import matplotlib.pyplot as plt
#from jpype import *
import os.path

class Stock:
    def __init__(self,rootWin):
        self.win=rootWin
        self.rtdir='/Users/chuhan/Dropbox/Classes/CSE6730/proj/'
        self.sv=['','','','','']
        for i in range(5):
            self.sv[i]=StringVar()
        
        
        l1=Label(self.win,width=20,text="1. Follow Trend")
        l1.grid(row=0,column=0,sticky=E)
        l2=Label(self.win,width=20,text="2. Buy and Hold")
        l2.grid(row=0,column=3,sticky=E)
        l3=Label(self.win,width=20,text="3. Momentum")
        l3.grid(row=1,column=0,sticky=E)
        l4=Label(self.win,width=20,text="4. Technical")
        l4.grid(row=1,column=3,sticky=E)
        l5=Label(self.win,width=20,text="Trade times:")
        l5.grid(row=2,column=0,sticky=E)
        
        ll=[]
        for i in range(4):
            ll.append(Label(self.win,text='%'))
            ll[i].grid(row=int(i/2),column=(i%2)*3+2,sticky=W)
        
        self.e1=Entry(self.win,width=10,textvariable=self.sv[0])
        self.e1.grid(row=0,column=1,sticky=E)
        self.e2=Entry(self.win,width=10,textvariable=self.sv[1])
        
        self.e2.grid(row=0,column=4,sticky=E)
        self.e3=Entry(self.win,width=10,textvariable=self.sv[2])
        self.e3.grid(row=1,column=1,sticky=E)
        self.e4=Entry(self.win,width=10,textvariable=self.sv[3])
        self.e4.grid(row=1,column=4,sticky=E)
        self.e5=Entry(self.win,width=10,textvariable=self.sv[4])
        self.e5.grid(row=2,column=1,sticky=E)
        
        b=Button(self.win,text="Run",command=self.clicked)
        b.grid(row=2,column=4)
    
    def clicked(self):
        if self.checkInput():
            self.javaInput()
            #self.runjava()
            #run java code with the input tmp_file
            #delete the file when java begins running
            self.input=self.rtdir+'trading_price_vol_cashVol.txt'
            if os.path.isfile(self.input):
                self.draw()
    
    def draw(self):
        f=open(self.input)
        tmp=f.readlines()
        f.close()
        p=[]
        for i in tmp:
            p.append(float(i.split()[0]))
        plt.plot(p)
        plt.ylabel('Price')
        plt.xlabel('# of trades')
        plt.show()

    def javaInput(self):
        f=open(self.rtdir+'tmp_file','w')
        for i in range(5):
            f.write(self.sv[i].get()+'\n')
        f.close()

    def runjava(self):
        pass
    
    def checkInput(self):
        pc=[]
        try:
            for i in range(4):
                pc.append(float(self.sv[i].get()))
            # print float(self.sv[i].get())
            Num=int(self.sv[-1].get())
        except ValueError:
            tkMessageBox.showinfo('Invalid Input!')
            return False
        
        total=0.0
        for i in range(4):
            total=total+pc[i]
            if pc[i]<0.0:
                tkMessageBox.showinfo('Prcentages should be positive numbers!')
                return False
        
        if total !=100.0:
            tkMessageBox.showinfo('Total percentage should be 100%')
            return False
        
        if Num <0:
            tkMessageBox.showinfo('Trade number should be an positive integer!')
            return False
        
        return True




Win=Tk()
App=Stock(Win)
Win.mainloop()



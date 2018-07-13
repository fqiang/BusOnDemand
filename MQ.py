from py4j.java_gateway import JavaGateway
import time


class parseMessage:
    def __init__(self,message):
        self.Message = message


    def gettopic(self):

        topic = self.Message.getTopic()
        return topic



    def gettag(self):
        tag = self.Message.getTag()
        if tag == "new":
            pass
        elif tag == " vehicle":
            pass
        elif tag == "stop":
            pass
        elif tag == "schedule":
            pass
        else:
            return False

    def getkey(self):
        key = self.Message.getKey()
        return key
    def getbody(self):
        body = self.Message.getBody()
        return body




if __name__ =="__main__":
    gateway = JavaGateway()
    while(1):

        message = gateway.getMessage()
        mes = parseMessage(message)
        body =mes.getbody()
        time.sleep(0.005)
        print(body)

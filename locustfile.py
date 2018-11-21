from locust import HttpLocust, TaskSet
import random

def postRandomNumber(l):
    randomNumber = random.randint(100000000,999999999)
    l.client.post("/numbers", {"number":randomNumber})
    
def postKnownDuplicate(l):
    l.client.post("/numbers", {"number":111111111})

class UserBehavior(TaskSet):
    tasks = {postRandomNumber: 10, postKnownDuplicate: 1}

    def on_start(self):
        postRandomNumber(self)

    def on_stop(self):
        postRandomNumber(self)

class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 5000
    max_wait = 9000
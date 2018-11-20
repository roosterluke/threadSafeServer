from locust import HttpLocust, TaskSet
import random

def postNumber(l):
    randomNumber = random.randint(100000000,999999999)
    l.client.post("/numbers", {"number":randomNumber})

class UserBehavior(TaskSet):
    tasks = {postNumber: 1}

    def on_start(self):
        postNumber(self)

    def on_stop(self):
        postNumber(self)

class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 5000
    max_wait = 9000
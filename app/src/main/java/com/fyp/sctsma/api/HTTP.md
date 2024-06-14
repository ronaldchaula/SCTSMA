# In this project we are going to use Retrofit an network library offered by square

#when making a request as a client 
# verb starts followed by address and a protocal then headers and body are optional
# when receiving a response then protocol and response are received together header and body are attached if there are any

# Some of HTTP verbs/methods are GET, HEAD, POST, PUT, PATCH, DELETE,TRACE,OPTIONS,CONNECT

# Retrofit supports GET, HEAD,POST,PUT,PATCH and DELETE

# HTTP GET requests data from the server
# another http method for reading server data is HEAD
# get and head are safe methods as they don't alter the state of the server only reading
# identical request can be made simultaneously
# get returns a response header while head does not
# head allow reading a file metadata without downloading it
# client can retrieve a collection of resources of just a specific record with an id
# / (forward slash) allow an item of data to be returned and we cana s well pass parameters using the url through query strings
# Methods that can modify the data on a server are DELETE, POST, PUT and PATCH
# Delete removes the resources
# Post creates/add the resources require the body of the request, it sends a payload through the body
# Put sets the state/data of the entire record using an id using the ide while patch just modify a specific field
# Using APIs in android
# android does not understand json hence the format needs to be changed into the primitive formats that an app can understand
# JSON and XML are two formats server send back the data to backend
# Data models or just models are the formats that an android app can udnerstand
# data converters are also called serializers
# Moshi, Gson, Jackson, Protobuf, Wire and Simple XML are the serializers(data converters available)
# Moshi is the one that will be used in this project

# Concurrency
# Android runs the user interface on the main thread and network calls usual take longer than usual hence they need to be channelled out of the main thread
# Concurrency can be channelled out using the Coroutines or regular threads
# asynchronous programming
# naturally getting result required callbacks now we have coroutines
# 
@GET()

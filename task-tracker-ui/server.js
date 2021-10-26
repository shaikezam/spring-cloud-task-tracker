const http = require('http');
const fs = require('fs');
const request = require('request');
var express = require('express');        // call express
var app = express();                 // define our app using express
var bodyParser = require('body-parser');

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(express.static(__dirname + '/public'))
var port = process.env.PORT || 4000;

var router = express.Router();

router.get('/', function (req, res) {
  res.sendFile(__dirname + "/index.html");
});

router.get('/webapi/tasks', function (req, res) {
  request('http://task-tracker-api-gateway:9000/tasks-app/api/tasks/', { json: true }, (err, response, body) => {
    console.log(err);
    console.log(body);
    if (err) {
      res.send(err);
    } else {
      res.json(body);
    }
  });
});

router.post('/webapi/tasks', function (req, res) {
  console.log("Send task: " + JSON.stringify(req.body));
  request('http://task-tracker-api-gateway:9000/tasks-app/api/tasks/', { json: true }, (err, response, body) => {
    request.post({
      headers: { 'content-type': 'application/json' },
      url: 'http://task-tracker-api-gateway:9000/tasks-app/api/tasks/',
      body: JSON.stringify(req.body)
    }, function (error, response, body) {
      res.status(202);
      res.send();
    });
  });
});

app.use('/', router);

app.listen(port);
console.log('Magic happens on port ' + port);


/*const server = http.createServer((req, res) => {

  if (req.url === "/webapi/tasks" && req.method === "GET") {

    request('http://task-tracker-api-gateway:9000/tasks-app/api/tasks/', { json: true }, (err, response, body) => {
      if (err) {
        return console.log(err);
      } else {
        res.writeHead(200, { 'Content-Type': 'application/json' });
        res.write(JSON.stringify(body));
        res.end();
      }

    });

  } else if (req.url === "/webapi/tasks" && req.method === "POST") {

    let data = '';
    req.on('data', chunk => {
      data += chunk;
    })
    req.on('end', () => {
      console.log(JSON.parse(data)); // 'Buy the milk'
      res.end();
      request.post({
        headers: { 'content-type': 'application/json' },
        url: 'http://task-tracker-api-gateway:9000/tasks-app/api/tasks/',
        body: JSON.parse(data)
      }, function (error, response, body) {
        console.log(body);
      });
    })

  } else if (req.url === "/js/script.js" && req.method === "GET") {
    res.writeHead(200, { 'content-type': 'text/html' })
    fs.createReadStream('public/js/script.js').pipe(res)
  } else if (req.url === "/" && req.method === "GET") {
    res.writeHead(200, { 'content-type': 'text/html' })
    fs.createReadStream('public/index.html').pipe(res)
  }

})

server.listen(process.env.PORT || 4000)*/
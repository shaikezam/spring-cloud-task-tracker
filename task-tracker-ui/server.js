const http = require('http');
const fs = require('fs');
const request = require('request');

const server = http.createServer((req, res) => {

  if (req.url === "/webapi/tasks" && req.method === "GET") {

    request('http://task-tracker-api-gateway:9000/tasks-app/api/tasks/', { json: true }, (err, response, body) => {
      if (err) {
        return console.log(err);
      } else {
        res.writeHead(200, {'Content-Type': 'text/plain'});
        res.write(body);
        res.end();
      }

    });

  } else if (req.url === "/js/script.js" && req.method === "GET") {
    res.writeHead(200, { 'content-type': 'text/html' })
    fs.createReadStream('public/js/script.js').pipe(res)
  } else if (req.url === "/" && req.method === "GET") {
    res.writeHead(200, { 'content-type': 'text/html' })
    fs.createReadStream('public/index.html').pipe(res)
  }

})

server.listen(process.env.PORT || 4000)
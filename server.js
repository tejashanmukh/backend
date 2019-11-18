 
const express = require('express')
const bodyParser = require('body-parser')

// get the routers
const productRouter = require('./product')
const categoryRouter = require('./category')

const app = express()
app.use(function(req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});
app.use(bodyParser.json())
app.use('/product', productRouter)
app.use('/category', categoryRouter)

app.get('/', (request, response) => {
    response.send(`<h1 style="color: green; text-align: center; font-family: arial;">welcome to final applications web services</h1>`)
})

app.listen(4000, '0.0.0.0', () => {
    console.log('server started  on port 4000')
})
const express = require('express')
const db = require('./db')
const utils = require('./utils')

const router = express.Router()

router.get('/', (request, response) => {
    const connection = db.connect()
    const statement = `select product.*, category.title as category_title from product, category where product.category_id = category.id `
    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})

router.post('/', (request, response) => {
    const {title, description, price, category_id} = request.body
    const connection = db.connect()
    const statement = `insert into product (title, description, price, category_id) values ('${title}', '${description}', '${price}', '${category_id}')`
    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})

router.put('/:id', (request, response) => {
    const {id} = request.params
    const {title, description, price, category_id} = request.body
    const connection = db.connect()
    const statement = `update product set title = '${title}', description = '${description}', price = '${price}', category_id = '${category_id}' where id = ${id}`
    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})

router.delete('/:id', (request, response) => {
    const {id} = request.params
    const connection = db.connect()
    const statement = `delete from product where id = ${id}`
    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})

module.exports = router
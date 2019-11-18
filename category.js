const express = require('express')
const db = require('./db')
const utils = require('./utils')

const router = express.Router()

router.get('/', (request, response) => {
    const connection = db.connect()
    const statement = `select * from category`
    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})

router.post('/', (request, response) => {
    const {title} = request.body
    const connection = db.connect()
    const statement = `insert into category (title) values ('${title}')`
    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})

router.put('/:id', (request, response) => {
    const {id} = request.params
    const {title} = request.body
    const connection = db.connect()
    const statement = `update category set title = '${title}' where id = ${id}`
    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})

router.delete('/:id', (request, response) => {
    const {id} = request.params
    const connection = db.connect()
    const statement = `delete from category where id = ${id}`
    connection.query(statement, (error, data) => {
        connection.end()
        response.send(utils.createResult(error, data))
    })
})

module.exports = router
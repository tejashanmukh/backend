const mysql = require('mysql')

function connect() {
    const connection = mysql.createConnection({
        host: 'localhost',
        user: 'root',
        password: 'root',
        database: 'test',
        port: 9099
    })

    connection.connect()

    return connection
}

module.exports = {
    connect: connect
}
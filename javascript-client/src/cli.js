import vorpal from 'vorpal'
import net from 'net'

import { hash } from './hashes'

const cli = vorpal()
const userList = {}

let server

cli.delimiter('Base: ')

const register = cli.command('register <username> <password>')
const login = cli.command('login <username> <password>')
const connect = cli.mode('connect <port> [host]')
// const allfiles = cli.command('allfiles')
// const upload = cli.command('upload <path>')
// const download = cli.command('download <id> [localpath]')

register
  .description('Registers a new user with <username> and <password>')
  .alias('r')
  .action(function (args, cb) {
    this.log('Howdy partner')
    // if the username matches a user in the list then pass a bool?
    return (Promise.resolve(userList[args.username] !== undefined)
      .then((exists) => {
        if (exists) {
          this.log('Username already registered! Choose another one.')
        } else {
          hash(args.password)
            .then((hashedPassword) => userList[args.username] = hashedPassword)
            .then(() => this.log(`Registered new User, ${args.username} ${args.password}`))
        }
      })
      .catch((err) => this.log(`An error occurred: ${err}`))
    )
  })

login
  .description('Login for existing username with <username> and <password>')
  .action(function (args, cb) {

  })

// allfiles
//   .description('Loads a list of all files stored on server')
//   .action(function (args, cb) {
//
//   })
//
// upload
//   .description('Uploads a file with the path <path> to the server')
//   .action(function (args, cb) {
//
//   })
//
// download
//   .description('downloads a file from the server with fileid <id> to an optional [localpath]')
//   .action(function (args, cb) {
//
//   })

connect
  .delimiter('Server -> ')
  .description('Mostly for testing connection to the server.')
  .init(function (args, callback) {
    server = net.createConnection(args, (connection) => {
      const address = server.address()
      this.log(`connected to server ${address.address}:${address.port}`)

      // RECIEVES data to print to the log
      server.on('data', (data) => {
        this.log(data.toString())
      })

      server.on('end', () => {
        this.log('disconnected from server :(')
        cli.exec('exit')
      })

      // server.write(`${username}\n`)
      callback()
    })
  })
  .action(function (command, callback) {
    // WRITES commands to the server
    server.write(`${command}\n`)
    callback()
  })

export default cli

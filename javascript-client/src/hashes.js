import crypto from 'bcryptjs'
import vorpal from 'vorpal'

const cli = vorpal()

export function hash (password) {
  let hashedPass = new Promise((resolve, reject) => {
    crypto.genSalt(function (err, salt) {
      if (err) {
        reject(err)
      } else {
        crypto.hash(password, salt, function (err, hashedPassword) {
          if (err) {
            reject(err)
          } else {
            resolve(hashedPassword)
          }
        })
      }
    })
  })
  return hashedPass
}

export function compare (password) {

}

export default {
  hash,
  compare
}

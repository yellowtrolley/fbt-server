# fairbiomarket

This README outlines the details of collaborating on this Ember application.
A short introduction of this app could easily go here.

## Prerequisites

You will need the following things properly installed on your computer.

* [Git](https://git-scm.com/)
* [Node.js](https://nodejs.org/) (with NPM)
* [Bower](https://bower.io/)
* [Ember CLI](https://ember-cli.com/)
* [PhantomJS](http://phantomjs.org/)

## Installation

* `git clone <repository-url>` this repository
* `cd fairbiomarket`
* `npm install`
* `bower install`

## Running / Development

* `ember serve`
* Visit your app at [http://localhost:4200](http://localhost:4200).

### Code Generators

Make use of the many generators for code, try `ember help generate` for more details

### Running Tests

* `ember test`
* `ember test --server`

### Building

* `ember build` (development)
* `ember build --environment production` (production)

### Deploying

Specify what it takes to deploy your app.

## Further Reading / Useful Links

* [ember.js](http://emberjs.com/)
* [ember-cli](https://ember-cli.com/)
* Development Browser Extensions
  * [ember inspector for chrome](https://chrome.google.com/webstore/detail/ember-inspector/bmdblncegkenkacieihfhpjfppoconhi)
  * [ember inspector for firefox](https://addons.mozilla.org/en-US/firefox/addon/ember-inspector/)



### Route hooks order
 * init() http://emberjs.com/api/classes/Ember.Route.html#method_init
 * beforeModel(transition) http://emberjs.com/api/classes/Ember.Route.html#method_beforeModel
 * model(params, transition) http://emberjs.com/api/classes/Ember.Route.html#method_model
 * afterModel(model, transition) http://emberjs.com/api/classes/Ember.Route.html#method_afterModel
 * activate() http://emberjs.com/api/classes/Ember.Route.html#method_activate
 * setupController(controller, model) http://emberjs.com/api/classes/Ember.Route.html#method_setupController
 * renderTemplate(controller, model) http://emberjs.com/api/classes/Ember.Route.html#method_renderTemplate



## http://www.ember-bootstrap.com/

## Deploy your app using Firebase Hosting service

 * $ npm install -g firebase-tools
 * $ ember build --prod
 * $ firebase login
 * $ firebase init


## File Upload with ember-file-upload

 * https://github.com/tim-evans/ember-file-upload







# fairbiomarket

This README outlines the details of collaborating on this Ember application.
A short introduction of this app could easily go here.


## Test Users
admin@example.com / admin123

## Firebase url
https://console.firebase.google.com/project/ember-fb80a

https://ember-fb80a.firebaseapp.com

## Run server
# Exploded
$ gradle bootRun

# As packaged app
$ java -jar build/libs/fairbiomarket-0.0.1-SNAPSHOT.jar

# You might also want to use this useful operating system environment variable:
$ export JAVA_OPTS=-Xmx1024m -XX:MaxPermSize=128M




# copy to server
scp -P 222 build/libs/fairbiomarket-0.0.1-SNAPSHOT.jar pablo@anecico.com:/home/pablo/codigoverde/server/

# to create a service in ubuntu
put codigoverde.sh in  ~/codigoverde
 
put codigoverde.service in the server's /etc/systemd/system/ folder and make it executable

systemctl enable codigoverde
run start, stop or restart
logs are in /tmp/spring.log
 

# Ferienpass-Admin

[![Build Status](https://travis-ci.org/nsiegmun/Ferienpass-Admin.svg?branch=master)](https://travis-ci.org/digital-bauhaus/Ferienpass-Admin)
[![License](http://img.shields.io/:license-mit-blue.svg)](https://github.com/nsiegmun/Ferienpass-Anmeldung/blob/master/LICENSE)

Hier entsteht die Administrations-Oberfläche für den digitalen Ferienpass der Stadt Weimar.

Jeder commit in den master löst einen Travis build aus. Wenn dieser inkl. Tests erfolgreich durchläuft, wird die Anwendung automatisch auf unsere QA-Stage deployed: https://ferienpass-admin.herokuapp.com

Das Projekt basiert auf dem repo [spring-boot-vuejs](https://github.com/jonashackt/spring-boot-vuejs).


## NodeJS, NPM, Vue.js

### Prerequisites

#### macOS

```
brew install node
npm install --global vue-cli
```

#### Ubuntu/Debian

Die Node-Version in den offiziellen Ubuntu-Repositories ist leider nicht sehr aktuell. Deshalb orientieren sich die folgenden Anweisungen an der Anleitung auf der NodeJS-Website ([Installing Node.js via package manager](https://nodejs.org/en/download/package-manager/#debian-and-ubuntu-based-linux-distributions)).

```
curl -sL https://deb.nodesource.com/setup_8.x | sudo -E bash -
sudo apt-get install nodejs
```

Leider ist `npm` standardmäßig nicht korrekt benutzbar, da es versucht, globale Pakete in einem Systemverzeichnis zu speichern. Auf diese hat ein Benutzer aber in der Regel keinen Zugriff. Mit Hilfe von `sudo` würden sich Pakete zwar global installieren lassen, aber diese funktionieren in Folge auch nur mit `sudo` korrekt.

Die NPM-Dokumentation hat dafür eine Lösung, die hier leicht abgewandelt wiedergegeben wird ([Change npm's default directory to another directory](https://docs.npmjs.com/getting-started/fixing-npm-permissions#option-2-change-npms-default-directory-to-another-directory)).

```
mkdir ~/.npm-global
npm config set prefix '~/.npm-global'
gedit ~/.bashrc
```

Die folgende Zeile sollte am Ende der Datei `~/.bashrc` angefügt werden.

```
export PATH=~/.npm-global/bin:$PATH
```

Anschließend wird die Datei in die aktuelle Terminal-Session geladen.

```
source ~/.bashrc
```

Schließlich lassen sich ordnungsgemäß NPM-Pakete global installieren.

```
npm install --global vue-cli
```

#### Windows

##### Packet-Manager

Dafür muss der Paketmanager Chocolatey installiert werden: https://chocolatey.org/install

```
choco install npm
npm install --global vue-cli
```

##### Installer

NodeJS und NPM lassen sich mit dem Installer von der Website installieren: https://nodejs.org/en/download.





## Project Setup

```
Ferienpass-Anmeldung
├─┬ backend     → backend module with Spring Boot
│ ├── src
│ └── pom.xml
├─┬ frontend    → frontend module with Vue.js
│ └── pom.xml
└── pom.xml     → Maven parent pom with child modules
```

### Tell Webpack to output the `dist/` contents to `target/dist/`

Commonly, node projects will create a dist/ directory for final builds which contains the minified source code of the web app - but we want it all in `target/`. Therefore go to `frontend/config/index.js` and replace the following lines:

```
index: path.resolve(__dirname, '../dist/index.html'),
assetsRoot: path.resolve(__dirname, '../dist'),
```

with

```
index: path.resolve(__dirname, '../target/dist/index.html'),
assetsRoot: path.resolve(__dirname, '../target/dist'),
```

### Import Project in IntelliJ IDEA

Um das Projekt in IntelliJ bauen zu können, muss es als Maven-Projekt eingerichtet werden.

Unter Umständen ist die Maven-Integration deaktiviert:

Unter *File* → *Settings...* → *Plugins*, suche nach ‘Maven’.

Aktiviere dort folgende Einträge:

- Maven Integration
- Maven Integration Extension

Dann lässt sich das Projekt über *File* → *New* → *Project from Existing Sources...* importieren. Dazu muss einfach die `pom.xml` im Hauptverzeichnis ausgewählt werden. Der sich öffnende Dialog kann ungeändert bestätigt werden.

Schließlich kann das Projekt über *Build* → *Build Project* kompiliert werden.





## First App Run

```
mvn clean install
```

Run our complete Spring Boot App:

```
mvn --projects backend spring-boot:run
```

Now go to http://localhost:8098/ and have a look at your first Vue.js Spring Boot App.



## Fast Feedback with `webpack-dev-server`

The `webpack-dev-server`, which will update and build every change through all the parts of the JavaScript build-chain, is pre-configured in Vue.js out-of-the-box! So the only thing needed to get fast feedback development-cycle is running the following:

```
cd frontend
npm run dev
```

That’s it!


## Browser Developer Tools Extension

Install vue-devtools Browser extension https://github.com/vuejs/vue-devtools and get better feedback.



## HTTP calls from Vue.js to (Spring Boot) REST backend

Prior to Vue 2.0, there was a build in soultion (vue-resource). But from 2.0 on, 3rd party libraries are necessary. One of them is [Axios](https://github.com/mzabriskie/axios) - also see blog post https://alligator.io/vuejs/rest-api-axios/

```
npm install axios --save
```

Calling a REST service with Axios is simple. Go into the script area of your component, e.g. Hello.vue and add:

```
import axios from 'axios'

data () {
  return {
    response: [],
    errors: []
  }
},

callRestService () {
  axios.get(`api/hello`)
    .then(response => {
      // JSON responses are automatically parsed.
      this.response = response.data
    })
    .catch(e => {
      this.errors.push(e)
    })
}
}
```

In your template area you can now request a service call via calling `callRestService()` method and access `response` data:

```
<button class=”Search__button” @click="callRestService()">CALL Spring Boot REST backend service</button>

<h3>{{ response }}</h3>
```

### The problem with SOP

Single-Origin Policy (SOP) could be a problem, if we want to develop our app. Because the webpack-dev-server runs on http://localhost:8090 and our Spring Boot REST backend on http://localhost:8098.

We need to use Cross Origin Resource Sharing Protocol (CORS) to handle that (read more background info about CORS here https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS)

#### Enabling Axios CORS support

Create a central Axios configuration file called `http-commons.js`:

```
import axios from 'axios'

export const AXIOS = axios.create({
  baseURL: `http://localhost:8098`,
  headers: {
    'Access-Control-Allow-Origin': 'http://localhost:8090'
  }
})
```

Here we allow requests to the base URL of our Spring Boot App on port 8098 to be accessable from 8090.

Now we could use this configuration inside our Components, e.g. in `Hello.vue`:

```
import {AXIOS} from './http-common'

export default {
  name: 'hello',

  data () {
    return {
      posts: [],
      errors: []
    }
  },
  methods: {
    // Fetches posts when the component is created.
    callRestService () {
      AXIOS.get(`hello`)
        .then(response => {
          // JSON responses are automatically parsed.
          this.posts = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })
    }
  }
```

#### Enabling Spring Boot CORS support

Additionally, we need to configure our Spring Boot backend to answer with the appropriate CORS HTTP Headers in it’s responses (theres a good tutorial here: https://spring.io/guides/gs/rest-service-cors/). Therefore we add the annotation `@CrossOrigin` to our BackendController:

```
@CrossOrigin(origins = "http://localhost:8090")
@RequestMapping(path = "/hello")
public @ResponseBody String sayHello() {
  LOG.info("GET called on /hello resource");
  return HELLO_TEXT;
}
```

Now our Backend will responde CORS-enabled and accepts requests from 8090. But as this only enables CORS on one method, we have to repeatately add this annotation to all of our REST endpoints, which isn’t a nice style. We should use a global solution to allow access with CORS enabled to all of our REST resources. This could be done in the `SpringBootVuejsApplication.class`:

```
// Enable CORS globally
@Bean
public WebMvcConfigurer corsConfigurer() {
  return new WebMvcConfigurerAdapter() {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
      registry.addMapping("/api/*").allowedOrigins("http://localhost:8090");
    }
  };
}
```

Now all calls to resources behind `api/` will return the correct CORS headers.

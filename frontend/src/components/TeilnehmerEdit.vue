<template>
    <html>
    <nav>
      <a href="/#/Verwaltung/" >Alle Veranstaltungen</a>
      <a href="/#/Veranstaltung/">Veranstaltung erstellen </a>
      <a href="/#/Teilnehmer/" class="selected" >Alle Teilnehmer</a>
      <a href="/#/TeilnehmerAdd/" >Teilnehmer erstellen</a>
    </nav>
    <main>
      <h1>Teilnehmerbearbeitung</h1>
      <form method="post" v-on:submit.prevent="updateUser">
        <h2>Allgemeine Informationen</h2>
        <label for ="lastName">Nachname: </label>
        <input type="text" id ="lastName" placeholder="Nachname" v-model="user.nachname" :value="user.nachname">
        <label for ="firstName">Vorname: </label>
        <input type="text" id="firstName" placeholder="Vorname" v-model="user.vorname" :value="user.vorname">
        <label for ="birthDate">Geburtstag: </label>
        <input type="text" id="birthDate" placeholder="Geburtstag" v-model="user.geburtsdatum" :value="user.geburtsdatum">
        <label for ="street">Straße: </label>
        <input type="text" id="street" placeholder="Straße" v-model="user.strasse" :value="user.strasse">
        <label for ="postcode">Postleitzahl: </label>
        <input type="text" id="postcode" placeholder="Postleitzahl" v-model="user.postleitzahl" :value="user.postleitzahl">
        <label for ="city">Stadt: </label>
        <input type="text" id="city" placeholder="Stadt" v-model="user.stadt" :value="user.stadt">
        <label for ="telephone">Telefonnummer: </label>
        <input type="text" id="telephone" placeholder="Telefonnummer" v-model="user.telefon" :value="user.telefon">
        <label for ="healthcare">Krankenversicherungsnummer: </label>
        <input type="text" id="healthcare" placeholder="Krankenversicherungsnummer" v-model="user.notrufnummer" :value="user.notrufnummer">

          <fieldset>
              <label><input v-model="user.erlaubeMedikamentation" type="checkbox" id="check">Darf behandelt werden</label><br/>
              <label><input v-model="user.darfAlleinNachHause" type="checkbox" id="check">Darf schwimmen</label><br/>
              <label><input v-model="user.darfReiten" type="checkbox" id="check">Darf reiten</label><br/>
              <label><input v-model="user.darfSchwimmen" type="checkbox" id="check">Darf alleine nach Hause gehen</label><br/>
              <label><input v-model="user.hatBezahlt" type="checkbox" id="check">Hat bezahlt</label><br/>
          </fieldset>

        <h2>Notfallkontaktdaten</h2>
        <label for ="user.notfallKontakt.name">Name, Vorname:</label>
        <input type="text" v-if="user.notfallKontakt.name" id="contactname" v-model="user.notfallKontakt.name" placeholder="Name" :value="user.notfallKontakt.name">
        <label for ="user.notfallKontakt.address">Addresse:</label>
        <input type="text" id="contactaddress" v-model="user.notfallKontakt.address" placeholder="Addresse" :value="user.notfallKontakt.address">
        <label for ="user.notfallKontakt.telephone">Telefon:</label>
        <input type="text" id="contacttelephone" v-model="user.notfallKontakt.telephone" placeholder="Telefon" :value="user.notfallKontakt.telephone">

        <h2>Arztdaten</h2>
        <label for ="user.arzt.name">Name, Vorname:</label>
        <input type="text" id="doctorname" placeholder="Name" v-model="user.arzt.name" :value="user.arzt.name">
        <label for ="user.arzt.address">Adresse:</label>
        <input type="text" id="doctoraddress" v-model="user.arzt.address" placeholder="Addresse" :value="user.arzt.address">
        <label for ="user.arzt.telephone">Telefon:</label>
        <input type="text" id="doctortelephone" v-model="user.arzt.telephone" placeholder="Telefon" :value="user.arzt.telephone">

        <h2>Einschränkungen</h2>
        <h3>Allergien</h3>
        <div v-if="user.allergien">
        <table>
        <tr><th>Name</th><th>Information</th><th>Löschen</th></tr>
         <tr v-for="(allergie, index) of user.allergien">
         <td><input type="text" id="allergie_n" placeholder="Keine Allergie" v-model="allergie.name" :value="allergie.name"></td>
         <td><input type="text" id="allergie_info" placeholder="-" v-model="allergie.information" :value="allergie.information"></td>
         <td><button v-on:click="deleteListItem(user.id,5,index)">Löschen</button></td>
        </tr>
        </table>
        </div>
        <h3>Krankheiten</h3>
        <div v-if="user.krankheiten">
        <table>
        <tr><th>Name</th><th>Information</th><th>Löschen</th></tr>
        <tr v-for="(krankheit, index) of user.krankheiten">
        <td><input type="text" id="krankheit_n" placeholder="Keine Krankheit" v-model="krankheit.name" :value="krankheit.name"></td>
        <td><input type="text" id="krankheit_info" placeholder="-" v-model="krankheit.information" :value="krankheit.information"></td>
        <td><button v-on:click="deleteListItem(user.id,2,index)">Löschen</button></td>
        </tr>
        </table>
        </div>
        <h3>Behinderungen</h3>
        <div v-if="user.behinderungen">
        <table>
        <tr><th>Name</th><th>Information</th><th>Code</th><th>Löschen</th></tr>
        <tr v-for="(behinderung, index) of user.behinderungen">
        <td><input type="text" id="behinderung_n" placeholder="Keine Behinderung" v-model="behinderung.name" :value="behinderung.name"></td>
        <td><input type="text" id="behinderung_info" placeholder="-" v-model="behinderung.information" :value="behinderung.information"></td>
        <td><input type="text" id="behinderung_code" placeholder="-" v-model="behinderung.code.d_code" :value="behinderung.code.d_code"></td>
        <td><button v-on:click="deleteListItem(user.id,3,index)">Löschen</button></td>
        </tr>
        </table>
        </div>
        <h3>Essensbesonderheiten</h3>
        <div v-if="user.essenLimitierungen">
        <table>
        <tr><th>Name</th><th>Information</th><th>Löschen</th></tr>
        <tr v-for="(essen, index) of user.essenLimitierungen">
        <td><input type="text" id="essen_n" placeholder="Keine Besonderheiten" v-model="essen.name" :value="essen.name"></td>
        <td><input type="text" id="essen_info" placeholder="-" v-model="essen.information" :value="essen.information"></td>
        <td><button v-on:click="deleteListItem(user.id,4,index)">Löschen</button></td>
        </tr>
        </table>
        </div>
        </div>

      <h2>Angemeldete Projekte</h2>
      <div v-if="projectsOfUser">
      <table>
      <tr><th>Name</th><th>Stornieren</th></tr>
      <tr v-for="(projekt, index) of projectsOfUser">
      <td><label for="projekt.name"> {{projekt.name}}</label></td>
      <td><button v-on:click="cancelProject(user.id,projekt.id)">Stornieren</button></td>
      </tr>
      </table>
      </div>

      <h2>Stornierte Projekte</h2>
      <div v-if="user.stornierteTeilnehmer">
      <table>
      <tr><th>Name</th><th>Aktivieren</th></tr>
      <tr v-for="(projekt, index) of user.angemeldeteProjekte">
      <td><label for="projekt.name">{{projekt.name}}</label></td>
      <td><button v-on:click="activateProject(user.id, projekt.id)">Reaktivieren</button></td>
      </tr>
      </table>
      </div>

      <h2>Zu folgendem Projekt eintragen:</h2>
      <div v-if="projectsOfUser">
      <table>
      <tr><th>Name</th><th>Anmelden</th></tr>
      <tr v-for="(projekt, index) of allAvailableProjects">
      <td><label for="projekt.name"> {{projekt.name}}</label></td>
      <td><button v-on:click="assignProject(user.id,projekt.id)">Eintragen</button></td>
      </tr>
      </table>
      </div>
      <input type="submit" value="Update">
    </form>
    </main>
      <div :class="popupClass">✔ Erfolgreich!</div>
    </html>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Teilnehmer',
  data () {
    return {
      user: [],
      projectsOfUser: [],
      allAvailableProjects: [],
      allRawProjects: [],
      canceldProjectsOfUser: [],
      popupClass: 'fadeOut',
      errors: []
    };
  },
  created () {
    this.getUserData()
    this.getProjectsOfUser()
    this.getAllProjects()
  },
  methods: {
    postProject () {
      var params = new URLSearchParams();
      var id = parseInt(this.$route.query.id);
      params.append('id', id);
      params.append('firstName', this.userFirstname);
      params.append('lastName', this.userLastname);
      params.append('birthDate', this.userBirthdate);
      params.append('street', this.userStreet);
      params.append('city', this.userCity);
      params.append('postcode', this.userPostcode);
      params.append('telephone', this.userTelephone);
      params.append('healthcareNr', this.userHealthcarenr);
      var treatment = (this.userAllowtreatment === true);
      var homealone = (this.userAllowhomealone === true);
      var riding = (this.userAllowride === true);
      var swimming = (this.userAllowswim === true);
      var payed = (this.userHaspayed === true);
      params.append('allowTreatment', treatment);
      params.append('allowHomeAlone', homealone);
      params.append('allowRiding', riding);
      params.append('allowSwimming', swimming);
      params.append('hasPayed', payed);
      params.append('contactName', this.userContactname);
      params.append('contactAddress', this.userContactaddress);
      params.append('contactTelephone', this.userContacttelephone);
      params.append('doctorName', this.userDoctorname);
      params.append('doctorAddress', this.userDoctoraddress);
      params.append('doctorTelephone', this.userDoctortelephone);
      axios.post('http://localhost:8088/api/updateuser', params)
      .then(response => {
        this.popupClass = 'fadeIn'
        var self = this;
        setTimeout(function () {
          self.popupClass = 'fadeOut';
        }, 2000);
      })
      .catch(e => {
        this.errors.push(e)
      })
    },
    updateUser () {
      axios.post('http://localhost:8088/api/cancelproject', {
        user_id: this.user.id,
        vorname: this.user.vorname,
        nachname: this.user.nachname,
        geburtsdatum: this.user.geburtsdatum,
        strasse: this.user.strasse,
        stadt: this.user.stadt,
        postleitzahlt: this.user.postleitzahl,
        telefon: this.user.telefon,
        notrufnummer: this.user.notrufnummer,
        medikamente: this.user.erlaubeMedikamentation,
        nachHause: this.user.darfAlleinNachHause,
        reiten: this.user.darfReiten,
        schwimmen: this.user.darfSchwimmen,
        bezahlt: this.user.bezahlt,
        kname: this.user.notfallKontakt.name,
        kaddress: this.user.notfallKontakt.address,
        ktelefon: this.user.notfallKontakt.telephone,
        aname: this.user.arzt.name,
        aaddress: this.user.arzt.address,
        atel: this.user.arzt.telephone
      })
        .then(response => {
          this.popupClass = 'fadeIn'
          var self = this;
          setTimeout(function () {
            self.popupClass = 'fadeOut';
          }, 2000);
        })
          .catch(e => {
            this.errors.push(e)
          })
    },
    deleteListItem (id, typeList, itemPos) {
    /* note that the numbers of variable typeList correspond to the index value of the enumeration ListType in the backend */
      axios.post('http://localhost:8088/api/deletelistitem', {
        user_id: id,
        type: typeList,
        item: itemPos
      })
      .then(response => {
        this.popupClass = 'fadeIn'
        var self = this;
        setTimeout(function () {
          self.popupClass = 'fadeOut';
        }, 2000);
      })
      .catch(e => {
        this.errors.push(e)
      })
    },
    cancelProject (id, projectId) {
      axios.post('http://localhost:8088/api/cancelproject', {
        user_id: id,
        project: projectId
      })
        .then(response => {
          this.popupClass = 'fadeIn'
          var self = this;
          setTimeout(function () {
            self.popupClass = 'fadeOut';
          }, 2000);
        })
          .catch(e => {
            this.errors.push(e)
          })
    },
    assignProject (id, projectId) {
      axios.post('http://localhost:8088/api/assignProject', {
        user: id,
        project: projectId
      })
        .then(response => {
          this.popupClass = 'fadeIn'
          var self = this;
          setTimeout(function () {
            self.popupClass = 'fadeOut';
          }, 2000);
        })
          .catch(e => {
            this.errors.push(e)
          })
      this.getProjectsOfUser()
    },
    getUserData () {
      var id = parseInt(this.$route.query.id);
      axios.get('http://localhost:8088/api/user/' + id)
        .then(response => {
          this.user = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })
    },
    getAllProjects () {
      axios.get('http://localhost:8088/api/allprojects')
        .then(response => {
          this.allRawProjects = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })
      for (var i = 0; i < this.allRawProjects.length; i++) {
        this.projectsOfUser.forEach(function (project) {
          if (project.id === this.allAvailableProjects[i].id) {
            this.allAvailableProjects[this.allAvailableProjects.length] = project
          }
        })
      }
    },
    getProjectsOfUser () {
      var id = parseInt(this.$route.query.id);
      axios.get('http://localhost:8088/api/projectsofid', {
        params: {
          userID: id
        }
      })
        .then(response => {
          this.projectsOfUser = response.data
        })
        .catch(e => {
          this.errors.push(e)
        })
    }
  }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


.limit {
  display: -block;
  background-color: #e5d8ae;
  padding: 5px;
  border-radius: 5px;
  margin: 2px;
  font-size: 15px;
}
</style>


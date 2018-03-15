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
      <form method="post" v-on:submit.prevent="postProject">
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
        <input type="text" id="contactname" v-model="user.notfallKontakt.name" placeholder="Name" :value="user.notfallKontakt.name">
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
         <tr v-for="allergie of user.allergien">
         <td><input type="text" id="allergie_n" placeholder="Keine Allergie" v-model="allergie.name" :value="allergie.name"></td>
         <td><input type="text" id="allergie_info" placeholder="-" v-model="allergie.information" :value="allergie.information"></td>
         <td></td>
        </tr>
        </table>
        </div>
        <h3>Krankheiten</h3>
        <div v-if="user.krankheiten">
        <span class="limit" v-for="krankheit of user.krankheiten">
        {{krankheit.name}}
        <span v-if="krankheit.information" :title="krankheit.information">
        </span>
        </span>
        </div>
        <h3>Behinderungen</h3>
        <div v-if="user.behinderungen">
        <span class="limit" v-for="behinderung of user.behinderungen">
        {{behinderung.name}}
        <span v-if="behinderung.information" :title="behinderung.information">
        </span>
        </span>
        </div>
        <h3>Essensbesonderheiten</h3>
        <div v-if="user.essenLimitierungen">
        <span class="limit" v-for="essen of user.essenLimitierungen">
        {{essen.name}}
        <span v-if="essen.information" :title="essen.information">
        </span>
        </span>
        </div>

        <h2>Angemeldete Projekte</h2>
        <div v-if="user.angemeldeteProjekte">
        <div class="project" v-for="project of user.angemeldeteProjekte">
        {{project.name}}
        </div>
        </div>
        <h2>Stornierte Projekte</h2>
        <div v-if="user.stornierungen">
        <div class="storno" v-for="storno of user.stornierungen">
        {{storno.name}}
        </div>
        </div>

        <input type="submit" value="Bearbeiten">
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
      popupClass: 'fadeOut',
      errors: []
    };
  },
  created () {
    var id = parseInt(this.$route.query.id);
    axios.get('http://localhost:8088/api/user/' + id)
    .then(response => {
      this.user = response.data
    })
    .catch(e => {
      this.errors.push(e)
    })
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
    deleteListItem (id, type, item) {
      var params = new URLSearchParams();
      params.append('user_id', id);
      params.append('type', type);
      params.append('item', item);
      axios.post('http://localhost:8088/api/deltelistitem', params)
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


<template>
    <html>
    <nav>
      <a href="/#/Verwaltung/" >Alle Veranstaltungen</a>
      <a href="/#/Veranstaltung/">Veranstaltung erstellen </a>
      <a href="/#/Teilnehmer/" class="selected" >Alle Teilnehmer</a>
      <a href="/#/TeilnehmerAdd/" >Teilnehmer erstellen</a>
      <a href="/#/Reservierung/" >Reservierungen</a>
    </nav>
    <main>
      <h1>Teilnehmerbearbeitung</h1>
      <form method="post" v-on:submit.prevent="postProject">
        <h2>Allgemeine Informationen</h2>
        <label for ="lastName">Nachname: </label>
        <input type="text" id ="lastName" placeholder="Nachname" v-model="userLastname" :value="user.lastName">
        <label for ="firstName">Vorname: </label>
        <input type="text" id="firstName" placeholder="Vorname" v-model="userFirstname" :value="user.firstName">
        <label for ="birthDate">Geburtstag: </label>
        <input type="text" id="birthDate" placeholder="Geburtstag" v-model="userBirthdate" :value="user.birthDate">
        <label for ="street">Straße: </label>
        <input type="text" id="street" placeholder="Straße" v-model="userStreet" :value="user.street">
        <label for ="postcode">Postleitzahl: </label>
        <input type="text" id="postcode" placeholder="Postleitzahl" v-model="userPostcode" :value="user.postcode">
        <label for ="city">Stadt: </label>
        <input type="text" id="city" placeholder="Stadt" v-model="userCity" :value="user.city">
        <label for ="telephone">Telefonnummer: </label>
        <input type="text" id="telephone" placeholder="Telefonnummer" v-model="userTelephone" :value="user.telephone">
        <label for ="healthcare">Krankenversicherungsnummer: </label>
        <input type="text" id="healthcare" placeholder="Krankenversicherungsnummer" v-model="userHealthcarenr" :value="user.healthcareNr">

          <fieldset>
              <label><input v-model="userAllowtreatment" type="checkbox" id="check">Darf behandelt werden</label><br/>
              <label><input v-model="userAllowhomealone" type="checkbox" id="check">Darf schwimmen</label><br/>
              <label><input v-model="userAllowride" type="checkbox" id="check">Darf reiten</label><br/>
              <label><input v-model="userAllowswim" type="checkbox" id="check">Darf alleine nach Hause gehen</label><br/>
              <label><input v-model="userHaspayed" type="checkbox" id="check">Hat bezahlt</label><br/>
          </fieldset>

        <h2>Notfallkontaktdaten</h2>
        <label for ="contactname">Name, Vorname:</label>
        <input type="text" id="contactname" placeholder="Name" :value="user.emergencyContact.name">
        <label for ="contactaddress">Addresse:</label>
        <input type="text" id="contactaddress" placeholder="Name" :value="user.emergencyContact.address">
        <label for ="contacttelephone">Telefon:</label>
        <input type="text" id="contacttelephone" placeholder="Name" :value="user.emergencyContact.telephone">

        <h2>Arztdaten</h2>
        <label for ="doctorname">Name, Vorname:</label>
        <input type="text" id="doctorname" placeholder="Name" :value="user.doctor.name">
        <label for ="doctoraddress">Addresse:</label>
        <input type="text" id="doctoraddress" placeholder="Name" :value="user.doctor.address">
        <label for ="doctortelephone">Telefon:</label>
        <input type="text" id="doctortelephone" placeholder="Name" :value="user.doctor.telephone">

        <h2>Einschränkungen</h2>
        <div v-if="user.limits">
        <span class="limit" v-for="limit of user.limits">
          {{limit.name}}
          <span v-if="limit.information" :title="limit.information">
            ?
          </span>
        </span>
        </div>

        <h2>Projekte</h2>
        <div v-if="user.projects">
        <div class="project" v-for="project of user.projects">
        {{project.name}}
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
      userLastname: '',
      userFirstname: '',
      userBirthdate: '',
      userStreet: '',
      userCity: '',
      userPostcode: '',
      userTelephone: '',
      userHealthcarenr: '',
      userAllowtreatment: '',
      userAllowhomealone: '',
      userAllowride: '',
      userAllowswim: '',
      userHaspayed: '',
      popupClass: 'fadeOut',
      errors: []
    };
  },
  created () {
    var id = parseInt(this.$route.query.id);
    axios.get('http://localhost:8088/api/user/' + id)
    .then(response => {
      this.user = response.data
      this.userLastname = this.user.lastName
      this.userFirstname = this.user.firstName
      this.userBirthdate = this.user.birthDate
      this.userStreet = this.user.street
      this.userCity = this.user.city
      this.userPostcode = this.user.postcode
      this.userTelephone = this.user.telephone
      this.userHealthcarenr = this.user.healthcareNr
      this.userAllowtreatment = this.user.allowTreatment
      this.userAllowhomealone = this.user.allowHomeAlone
      this.userAllowride = this.user.allowRiding
      this.userAllowswim = this.user.allowSwimming
      this.userHaspayed = this.user.hasPayed
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
      axios.post('http://localhost:8088/api/updateuser', params)
      .then(response => {})
      .catch(e => {
        this.errors.push(e)
      })
      this.popupClass = 'fadeIn'
      var self = this;
      setTimeout(function () {
        self.popupClass = 'fadeOut';
      }, 2000);
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


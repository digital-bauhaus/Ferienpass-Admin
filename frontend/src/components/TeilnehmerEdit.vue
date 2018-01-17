<template>
    <html>
    <nav>
      <a href="/#/Veranstaltung/" >Veranstaltung erstellen</a>
      <a href="/#/Verwaltung/" >Veranstaltungen verwalten</a>
      <a href="/#/Teilnehmer/" >Teilnehmer</a>
      <a href="/#/TeilnehmerAdd/" >Teilnehmer erstellen</a>
      <a href="/#/Reservierung/" >Reservierungen</a>
    </nav>
    <main>
      <h1>Teilnehmerbearbeitung</h1>
      <form method="post" v-on:submit.prevent="postProject">
        <span class="caption">Teilnehmer bearbeiten:</span> <br/>
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
        <label for ="treatment">Behandlung: </label>
        <input type="text" id="treatment" placeholder="Behandlung" v-model="userAllowtreatment" :value="user.allowTreatment">
        <input type="submit" v-on:click="created()" value="Hinzufügen">
      </form>
    </main>
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
      this.userAllowswim = this.user.allowSwim
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
      var treatment = (this.userAllowtreatment === 'true');
      var homealone = (this.userAllowhomealone === 'true');
      var riding = (this.userAllowride === 'true');
      var swimming = (this.userAllowswim === 'true');
      var payed = (this.userHaspayed === 'true');
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
    },
    kill (event) {
      event.target.parentElement.parentElement.remove();
    }
  }
}

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>


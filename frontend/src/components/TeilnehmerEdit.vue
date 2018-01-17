<template>
    <html>
    <nav>
      <a href="/#/Veranstaltung/" >Veranstaltung erstellen</a>
      <a href="/#/Verwaltung/" >Veranstaltungen verwalten</a>
      <a href="/#/Teilnehmer/" >Teilnehmer</a>
      <a href="/#/Reservierung/" >Reservierungen</a>
    </nav>
    <main>
      <h1>Teilnehmerbearbeitung</h1>
      <form>
        <span class="caption">Teilnehmer bearbeiten:</span> <br/>
        <label for ="lastName">Nachname: </label>
        <input type="text" id ="lastName" placeholder="Nachname" :value="user.lastName">
        <label for ="firstName">Vorname: </label>
        <input type="text" id="firstName" placeholder="Vorname" :value="user.firstName">
        <label for ="birthDate">Geburtstag: </label>
        <input type="text" id="birthDate" placeholder="Geburtstag" :value="user.birthDate">
        <label for ="street">Straße: </label>
        <input type="text" id="street" placeholder="Straße" :value="user.street">
        <label for ="postcode">Postleitzahl: </label>
        <input type="text" id="postcode" placeholder="Postleitzahl" :value="user.postcode">
        <label for ="city">Stadt: </label>
        <input type="text" id="city" placeholder="Stadt" :value="user.city">
        <label for ="telephone">Telefonnummer: </label>
        <input type="text" id="telephone" placeholder="Telefonnummer" :value="user.telephone">
        <label for ="healthcare">Krankenversicherungsnummer: </label>
        <input type="text" id="healthcare" placeholder="Krankenversicherungsnummer" :value="user.healthcareNr">
        <label for ="treatment">Behandlung: </label>
        <input type="text" id="treatment" placeholder="Behandlung" :value="user.allowTreatment">
        <input type="submit" v-on:click="create()" value="Hinzufügen">
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
    kill (event) {
      event.target.parentElement.parentElement.remove();
    }
  }
}

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>


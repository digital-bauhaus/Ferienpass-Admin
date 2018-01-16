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
        <input type="text" placeholder="Nachname" :value="user.lastName">
        <input type="text" placeholder="Vorname" :value="user.firstName">
        <input type="text" placeholder="Geburtstag" :value="user.birthDate">
        <input type="text" placeholder="Straße" :value="user.street">
        <input type="text" placeholder="Postleitzahl" :value="user.postcode">
        <input type="text" placeholder="Stadt" :value="user.city">
        <input type="text" placeholder="Telefonnummer" :value="user.telephone">
        <input type="text" placeholder="Krankenversicherungsnummer" :value="user.healthcareNr">
        <input type="text" placeholder="Behandlung" :value="user.allowTreatment">
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


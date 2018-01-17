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
      <div>
        <h1>Teilnehmer erstellen</h1>
        <br/>
        <form>
          <input type="text" placeholder="Nachname"> <br/>
          &nbsp <br/>
          <input type="text" placeholder="Vorname"> <br/>
          &nbsp <br/>
          <input type="text" placeholder="Geburtstag"><br/>
          &nbsp <br/>
          <input type="text" placeholder="Straße"><br/>
          &nbsp <br/>
          <input type="text" placeholder="Postleitzahl"><br/>
          &nbsp <br/>
          <input type="text" placeholder="Stadt"> <br/>
          &nbsp <br/>
          <input type="text" placeholder="Telefonnummer"><br/>
          &nbsp <br/>
          <input type="text" placeholder="Krankenversicherungsnummer"><br/>
          &nbsp <br/>
          <input type="text" placeholder="Behandlung"><br/>
          &nbsp <br/>
          <fieldset>
            <label>
              <input type="checkbox" value="behandeln" id="check">Darf behandelt werden
            </label>
            <label>
              <input type="checkbox" value="schwimmen" id="check">Darf schwimmen
            </label>
            <label>
              <input type="checkbox" value="reiten" id="check">Darf reiten
            </label>
            <label>
              <input type="checkbox" value="hause" id="check">Darf alleine nach Hause gehen
            </label>
          </fieldset>
          &nbsp <br/>
          <input type="submit" v-on:click="create()" value="Hinzufügen">
        </form>
      </div>
    </main>
    <footer>
      <a href="/#/Test/" >Zurück zur Übersicht</a>
    </footer>
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
    var id = parseFloat(this.$route.query.id);
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


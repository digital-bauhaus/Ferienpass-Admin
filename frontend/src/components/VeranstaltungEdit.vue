<template>
    <html>
    <nav>
      <a href="/#/Veranstaltung/" >Veranstaltung erstellen</a>
      <a href="/#/Verwaltung/" >Veranstaltungen verwalten</a>
      <a href="/#/Teilnehmer/" >Teilnehmer</a>
      <a href="/#/Reservierung/" >Reservierungen</a>
    </nav>
    <main>
      <form>
        <span class="caption">Veranstaltung hinzufügen:</span> <br/>
        <label for ="label">Veranstaltungsname: </label>
        <input type="text" id="label" placeholder="Veranstaltungsname" :value="project.name">
        <label for ="date">Datum: </label>
        <input type="text" id="date" placeholder="Datum (TT.MM.JJJ)" :value="project.date">
        <label for ="num">Plätze (gesamt): </label>
        <input type="text" id="num" placeholder="Plätze" :value="project.slots">
        <label for ="reserve">Plätze (reserviert): </label>
        <input type="text" id="reserve" placeholder="Reservierte Plätze" :value="project.slotsReserved">
        <label for ="age">Alter: </label>
        <input type="text" id="age" placeholder="Altersbegrenzung" :value="project.age">
        <label for ="price">Preis: </label>
        <input type="text" id="price" placeholder="Preis" :value="project.price">
        <label for ="weblink">Weblink: </label>
        <input type="text" id="weblink" placeholder="Weblink" :value="project.weblink">
        <input type="submit" v-on:click="create()" value="Hinzufügen">
      </form>
    </main>
    </html>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Veranstaltung',
  data () {
    return {
      project: [],
      errors: []
    };
  },
  created () {
    var id = parseInt(this.$route.query.id);
    axios.get('http://localhost:8088/api/project/' + id)
    .then(response => {
      this.project = response.data
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


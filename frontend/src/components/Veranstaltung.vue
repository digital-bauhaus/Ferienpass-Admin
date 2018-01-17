</template>
<template xmlns:v-on="http://www.w3.org/1999/xhtml">
    <html>
    <nav>
      <a href="/#/Veranstaltung/" >Veranstaltung erstellen</a>
      <a href="/#/Verwaltung/" >Veranstaltungen verwalten</a>
      <a href="/#/Teilnehmer/" >Teilnehmer</a>
      <a href="/#/Reservierung/" >Reservierungen</a>
    </nav>
    <main>
      <h1>Veranstaltungsformular</h1>
      <form method="post" v-on:submit.prevent="postProject">
            <span class="caption">Veranstaltung hinzufügen:</span> <br/>
            <input type="text" name="label" v-model="projectName" placeholder="Veranstaltungsname" required>
            <input type="text" name="org" v-model="projectDate" placeholder="Datum (TT.MM.JJJ)" required>
            <input type="text" name="num" v-model="projectSlots" placeholder="Plätze" required>
            <input type="text" name="num" v-model="projectSlotsreserved" placeholder="Reservierte Plätze" required>
            <input type="text" name="num" v-model="projectAge" placeholder="Altersbegrenzung" required>
            <input type="text" name="num" v-model="projectPrice" placeholder="Preis" required>
        <input type="text" name="num" v-model="projectSlotsfree" placeholder="Plätze frei" required>
        <input type="text" name="num" v-model="projectWeblink" placeholder="Weblink" required>

        <input type="submit" value="Hinzufügen">
          </form>
    </main>
    </html>
</template>


<script>
import axios from 'axios';

export default {
  name: 'Veranstaltung-erstellen',
  data () {
    return {
      title: 'Veranstaltung erstellen',
      projectName: '',
      projectDate: '',
      projectAge: '',
      projectPrice: '',
      projectSlots: '',
      projectSlotsfree: '',
      projectSlotsreserved: '',
      projectWeblink: '',
      errors: []
    }
  },
  methods: {
    postProject () {
      var params = new URLSearchParams();
      params.append('name', this.projectName);
      params.append('date', this.projectDate);
      params.append('age', parseInt(this.projectAge));
      params.append('price', parseInt(this.projectPrice));
      params.append('slots', parseInt(this.projectSlots));
      params.append('slotsFree', parseInt(this.projectSlotsfree));
      params.append('slotsReserved', parseInt(this.projectSlotsreserved));
      params.append('weblink', this.projectWeblink);
      axios.post('http://localhost:8088/api/createproject', params)
      .then(response => {})
      .catch(e => {
        this.errors.push(e)
      })
    },
    create () {
      alert('Sie haben das Event erstellt');
    }
  }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>


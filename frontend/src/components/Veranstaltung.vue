</template>
<template>
    <html>
    <nav>
      <a href="/#/Veranstaltung/" >Veranstaltung erstellen</a>
      <a href="/#/Verwaltung/" >Veranstaltungen verwalten</a>
      <a href="/#/Teilnehmer/" >Teilnehmer</a>
      <a href="/#/Reservierung/" >Reservierungen</a>
    </nav>
    <main>
      <input type="text" v-model="projectName" @change="postProject()"/>
      <h1>Veranstaltungsformular</h1>
      <form>
            <span class="caption">Veranstaltung hinzufügen:</span> <br/>
            <input type="text" name="label" placeholder="Veranstaltungsname" required>
            <input type="text" name="date" placeholder="Zeitraum" required>
            <input type="text" name="org" placeholder="Datum (TT.MM.JJJ)" required>
            <input type="text" name="num" placeholder="Plätze" required>
            <input type="text" name="num" placeholder="Reservierte Plätze" required>
            <input type="text" name="num" placeholder="Altersbegrenzung" required>
            <input type="text" name="num" placeholder="Preis" required>
            <input type="submit" v-on:click="create()" value="Hinzufügen" required>
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
  postProject () {
    axios.post('http://localhost:8088/api/createproject', {
      name: this.projectName,
      date: this.projectDate,
      age: this.projectAge,
      price: this.projectPrice,
      slots: this.projectSlots,
      slotsFree: this.projectsSlotsfree,
      slotsReserved: this.projectsSlotsreserved
    })
    .then(response => {})
    .catch(e => {
      this.errors.push(e)
    })
  },
  methods: {
    create () {
      alert('Sie haben das Event erstellt');
    }
  }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>


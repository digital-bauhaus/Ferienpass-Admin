<template>
    <html>
    <nav>
      <a href="/#/Verwaltung/" >Alle Veranstaltungen</a>
      <a href="/#/Veranstaltung/" class="selected" >Veranstaltung erstellen </a>
      <a href="/#/Teilnehmer/" >Alle Teilnehmer</a>
      <a href="/#/TeilnehmerAdd/" >Teilnehmer erstellen</a>
      <a href="/#/Reservierung/" >Reservierungen</a>
    </nav>
    <main>
    <h1>Veranstaltungsbearbeitung</h1>
      <form method="post" v-on:submit.prevent="postProject">
        <span class="caption">Veranstaltung bearbeiten:</span> <br/>
        <label for ="label">Veranstaltungsname: </label>
        <input type="text" id="label" placeholder="Veranstaltungsname" v-model="projectName" :value="project.name">
        <label for ="date">Datum: </label>
        <input type="text" id="date" placeholder="Datum (TT.MM.JJJ)" v-model="projectDate" :value="project.date">
        <label for ="num">Plätze (gesamt): </label>
        <input type="text" id="num" placeholder="Plätze" v-model="projectSlots" :value="project.slots">
        <label for ="reserve">Plätze (reserviert): </label>
        <input type="text" id="reserve" placeholder="Reservierte Plätze" v-model="projectSlotsreserved" :value="project.slotsReserved">
        <label for ="age">Alter: </label>
        <input type="text" id="age" placeholder="Altersbegrenzung" v-model="projectAge" :value="project.age">
        <label for ="price">Preis: </label>
        <input type="text" id="price" placeholder="Preis" v-model="projectPrice" :value="project.price">
        <label for ="weblink">Weblink: </label>
        <input type="text" id="weblink" placeholder="Weblink" v-model="projectWeblink" :value="project.weblink">
        <input type="submit" v-on:click="create()" value="Bearbeiten">
      </form>
    </main>
    <div :class="popupClass">✔ Erfolgreich bearbeitet!</div>
    </html>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Veranstaltung',
  data () {
    return {
      project: [],
      projectName: '',
      projectDate: '',
      projectAge: '',
      projectPrice: '',
      projectSlots: '',
      projectSlotsfree: '',
      projectSlotsreserved: '',
      projectWeblink: '',
      errors: [],
      popupClass: 'fadeOut'
    };
  },
  created () {
    var id = parseInt(this.$route.query.id);
    axios.get('http://localhost:8088/api/project/' + id)
    .then(response => {
      this.project = response.data
      this.projectName = this.project.name
      this.projectDate = this.project.date
      this.projectAge = this.project.age
      this.projectPrice = this.project.price
      this.projectSlots = this.project.slots
      this.projectSlotsfree = this.project.slotsFree
      this.projectSlotsreserved = this.project.slotsReserved
      this.projectWeblink = this.project.weblink
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
      params.append('name', this.projectName);
      params.append('date', this.projectDate);
      params.append('age', parseInt(this.projectAge));
      params.append('price', parseInt(this.projectPrice));
      params.append('slots', parseInt(this.projectSlots));
      params.append('slotsFree', parseInt(this.projectSlotsfree));
      params.append('slotsReserved', parseInt(this.projectSlotsreserved));
      params.append('weblink', this.projectWeblink);
      axios.post('http://localhost:8088/api/updateProject', params)
      .then(response => {})
      .catch(e => {
        this.errors.push(e)
      })

      this.popupClass = 'fadeIn'
      var self = this;
      setTimeout(function () {
        self.popupClass = 'fadeOut';
      }, 2000);
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


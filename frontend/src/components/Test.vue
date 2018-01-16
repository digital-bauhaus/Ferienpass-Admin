<template>
    <html>
    <nav>
      <a href="/#/Veranstaltung/" >Veranstaltung erstellen</a>
      <a href="/#/Verwaltung/" >Veranstaltungen verwalten</a>
      <a href="/#/Teilnehmer/" >Teilnehmer</a>
      <a href="/#/Reservierung/" >Reservierungen</a>
    </nav>
    <main>
      <div class="front">
      <div class="title">{{title}}</div><hr />
      <div class="subtitle">{{subtitle}}</div>
      </div>
    </main>
    </html>
</template>


<script>

export default {
  name: 'Test',

  data () {
    return {
      title: 'Ferienpass Weimar',
      subtitle: 'Administration'
    }
  }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>


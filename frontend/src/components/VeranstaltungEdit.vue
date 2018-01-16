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
      <form>
            <span class="caption">Veranstaltung hinzufügen:</span> <br/>
            <input type="text" name="label" placeholder="Veranstaltungsname">
            <input type="text" name="date" placeholder="Zeitraum"
            <input type="text" name="org" placeholder="Datum (TT.MM.JJJ)">
            <input type="text" name="num" placeholder="Plätze">
            <input type="text" name="num" placeholder="Reservierte Plätze">
            <input type="text" name="num" placeholder="Altersbegrenzung">
            <input type="text" name="num" placeholder="Preis">
            <input type="submit" v-on:click="create()" value="Hinzufügen">
          </form>
    </main>
    </html>
</template>

<script>

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>


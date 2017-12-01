<template>
	<div>
		<h2>{{title}}</h2>
		Erstellen von Veranstaltung <br/>
		Übersicht über Veranstaltung <br/>
		Übersicht über Kinder in der jeweiligen Veranstaltung <br/>
		Editing <br/>
		<br/>

		<br/>
		<b>Event bearbeiten:</b><br/>
		<b>Verwaltung</b>:
		Ich als Admin möchte die Anmeldung zu allen Veranstaltungen einsehen und verwalten können. <br/>
		-> eventuell Button, drauf klicken und dann weiterleitung an datenbank bzw erstmal dummy daten <br/>
		<br/>
		<b>Reservierung:</b>
		Ich als Admin möchte eine gewisse Anzahl von Veranstaltungsplätzen reservieren können und wieder freigeben können. <br/>
		-> Reservierung über eine Nummer die dann gesendet wird, platz für veranstaltung eingeben <br/>
		<br/>
		<b>Reporting </b>:
		Ich als Admin möchte die Anmeldung zu einer Veranstaltung exportieren können. Vielleicht als veranstaltungsname.pdf <br/>
		-> veranstaltung einsehen, über datenbank und dann exportieren <br/>
		<br/>
		<b>Stornierung</b>:
		Ich als Admin möchte bestimmte Anmeldungen stornieren können. <br/>
		-> eintrag aus datenbank entfernen <br/>
		<br/>
		<h1>Hello App!</h1>
		<p>
			<a href="/#/Veranstaltung/" >Veranstaltung erstellen</a>
		</p>
    <p>
      <a href="/#/Verwaltung/" >Veranstaltungen verwalten</a>
    </p>
    <p>
      <a href="/#/Teilnehmer/" >Teilnehmer</a>
    </p>
    <p>
      <a href="/#/Reservierung/" >Reservierungen</a>
    </p>
	</div>
</template>


<script>

export default {
  name: 'Test',

  data () {
    return {
      title: 'Admin Oberfläche'
    }
  }
}
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>


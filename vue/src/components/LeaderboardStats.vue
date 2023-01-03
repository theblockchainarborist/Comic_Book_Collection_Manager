<template>
  <div id="top-div">
      <div id="box">

      
        <h1 id="leaderboard-title">L e a d e r b o a r d</h1>
            
        <div id="leaderboard-stats">

            <label class="switch">
                <input class="switch-input" type="checkbox" v-on:click="showCollections = !showCollections" />
                <span class="switch-label" data-on="Most Collections" data-off="Most Comics"></span> 
                <span class="switch-handle"></span> 
            </label>

            <div id="stats">

                <div id="collection-leaderboard">
                    <div v-if="showCollections">
						<!-- <h2 style="margin-right: 100px;">Top Users: Collections</h2> -->
                        <p v-for="user in leaderboard.userWithMostCollections" v-bind:key="user.id">&#127942; {{user}} collection(s)</p> 
                    </div>
                    
                </div>
                <div id="comic-leaderboard">
                    <div v-if="!showCollections" >
                        <!-- <h2>Top Users: Comics</h2> -->
                        <p v-for="user in leaderboard.userWithMostComics" v-bind:key="user.id">&#127942; {{user}} comics/collection</p>  
                    </div>
                    
                </div>

            </div>

        </div>

        
     </div>
        
        
  </div>
</template>

<script>
import collectionService from '../services/CollectionService.js';

export default {
    name: "LeaderboardStats",
    data(){
        return{
            leaderboard: {},
            showCollections: false
        }
    },
    created() {
        collectionService.getLeaderBoard().then(response => {
            if(response.status === 200){
                this.leaderboard = response.data;
            }
        })
    }
}
</script>

<style scoped>
#leaderboard-stats {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

#leaderboard-title {
    text-align: center;
	color: #fff;
	text-shadow:
		0 0 7px #fff,
		0 0 92px rgb(250, 163, 3),
		0 0 102px rgb(250, 163, 3),
		0 0 151px rgb(250, 163, 3);
}

p {
    text-align: left;
	padding: 0.35rem;
	color: #fff;
	text-shadow:
		0 0 7px #fff,
		0 0 92px rgb(250, 163, 3),
		0 0 102px rgb(250, 163, 3),
		0 0 151px rgb(250, 163, 3);
	margin: 0.25rem;
	
}

#box {
	border: 0.2rem solid #fff;
	border-radius: 2rem;
	padding: 1em;
	box-shadow: 0 0 .2rem #fff,
              0 0 .2rem #fff,
              0 0 2rem #fee613,
              0 0 0.8rem #fee613,
              inset 0 0 1.3rem #fee613;
	width: 50vw;
	margin: 30px;
}

.switch {
	position: relative;
	display: block;
	vertical-align: top;
	width: 130px;
	height: 20px;
	padding: 3px;
	margin: 0 10px 10px 0;
	background: linear-gradient(to bottom, #eeeeee, #FFFFFF 25px);
	background-image: -webkit-linear-gradient(top, #eeeeee, #FFFFFF 25px);
	border-radius: 18px;
	box-shadow: inset 0 -1px white, inset 0 1px 1px rgba(0, 0, 0, 0.05);
	cursor: pointer;
	box-sizing:content-box;
}
.switch-input {
	position: absolute;
	top: 0;
	left: 0;
	opacity: 0;
	box-sizing:content-box;
}
.switch-label {
	position: relative;
	display: block;
	height: inherit;
	font-size: 10px;
	text-transform: uppercase;
	background: #f80b03;
	border-radius: inherit;
	box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.12), inset 0 0 2px rgba(0, 0, 0, 0.15);
	box-sizing:content-box;
}
.switch-label:before, .switch-label:after {
	position: absolute;
	top: 50%;
	margin-top: -.5em;
	line-height: 1;
	-webkit-transition: inherit;
	-moz-transition: inherit;
	-o-transition: inherit;
	transition: inherit;
	box-sizing:content-box;
}
.switch-label:before {
	content: attr(data-off);
	right: 11px;
	color: white;
	text-shadow: 0 1px rgba(255, 255, 255, 0.5);
}
.switch-label:after {
	content: attr(data-on);
	left: 11px;
	color: black;
	text-shadow: 0 1px rgba(0, 0, 0, 0.2);
	opacity: 0;
}
.switch-input:checked ~ .switch-label {
	background: #E1B42B;
	box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.15), inset 0 0 3px rgba(0, 0, 0, 0.2);
}
.switch-input:checked ~ .switch-label:before {
	opacity: 0;
}
.switch-input:checked ~ .switch-label:after {
	opacity: 1;
}
.switch-handle {
	position: absolute;
	top: 4px;
	left: 4px;
	width: 18px;
	height: 18px;
	background: linear-gradient(to bottom, #FFFFFF 40%, #f0f0f0);
	background-image: -webkit-linear-gradient(top, #FFFFFF 40%, #f0f0f0);
	border-radius: 100%;
	box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.2);
}
.switch-handle:before {
	content: "";
	position: absolute;
	top: 50%;
	left: 50%;
	margin: -6px 0 0 -6px;
	width: 12px;
	height: 12px;
	background: linear-gradient(to bottom, #eeeeee, #FFFFFF);
	background-image: -webkit-linear-gradient(top, #eeeeee, #FFFFFF);
	border-radius: 6px;
	box-shadow: inset 0 1px rgba(0, 0, 0, 0.02);
}
.switch-input:checked ~ .switch-handle {
	left: 114px;
	box-shadow: -1px 1px 5px rgba(0, 0, 0, 0.2);
}
 
/* Transition
========================== */
.switch-label, .switch-handle {
	transition: All 0.3s ease;
	-webkit-transition: All 0.3s ease;
	-moz-transition: All 0.3s ease;
	-o-transition: All 0.3s ease;
}

</style>
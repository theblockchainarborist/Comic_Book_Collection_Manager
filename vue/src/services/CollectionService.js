import axios from 'axios';


const http = axios.create({
    baseURL: "http://localhost:9000",
});

export default {
    getAllPublic() {
        return http.get('/collections');
    },

    getComicsFromCollectionId(id, token) {
        if (token) {
            return http.get(`/collections/${id}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
        }
        else {
            return http.get(`/collections/${id}`);
        }
        
    },
    
    getCollectionsFromUser(username, token) {
        //alert("Service Username = " + username)
        //console.log( "Token = " + token)
        let response = http.get(`/users/${username}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        });
        //alert( "Service Response : " + JSON.stringify(response))
        return response;
    },

    addNewCollection(username, token, collection) {
        let config = {
            headers: {Authorization: `Bearer ${token}`}
        };
        return http.post(`/users/${username}`, collection, config);
    },

    searchComics(title) {
        return http.get('/comics', { params: { title: title } });
    },

    addNewComic(collID, apiID, token) {
        let body = {"apiId": apiID};
        let config = {
            headers: {Authorization: `Bearer ${token}`}
        };
        return http.post(`/collections/${collID}`, body, config);
    },

    getComicDetails(comicID) {
        return http.get(`/comics/${comicID}`);
    },

    getAllAggStats() {
            return http.get('/stats');
    },

    getCollectionStats(collectionId, token) {
        //alert("")
        let config = {
            headers: {Authorization: `Bearer ${token}`}
        };
        let data = http.get(`/stats/${collectionId}`, config);
        //alert(JSON.stringify(data))
        return data;
    },

    getLeaderBoard(){
        return http.get('/leaderboard');
    },

    getSharedCollectionLink(collectionId){
        return http.get(`/shared/collections/${collectionId}`);
    }
    
}
<%-- 
    Document   : criteria
    Created on : Apr 17, 2024, 4:19:09 PM
    Author     : AnhTH
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./style/criteria.css">
    </head>
    <body>
     <h1>Criteria Information</h1>
    <table border="1">
        <tr>
            <th>Criteria ID</th>
            <th>Criteria Name</th>
            <th>Description</th>
        </tr>
        <c:forEach var="criteria" items="${criteriaList}">
            <tr>
                <td>${criteria.criteriaID}</td>
                <td>${criteria.criteriaName}</td>
                <td>${criteria.description}</td>
            </tr>
        </c:forEach>
    </table>
        <div id="page">
  <header>
    <h2>Essay Criteria</h2>
  </header>
  <main>
    <div id="tabs">
      <div class="tab-links">
        <button data-id="prepare" data-default>Criteria 1</button>
        <button data-id="produce">Criteria 2</button>
        <button data-id="delivery">Criteria 3</button>
      </div>
      
      <div class="tab-content">
        <section id="prepare">
          <h4>How to prepare content?</h4>
          <p>Need a lot of research and organizing ideas</p>
        </section>
        <section id="produce">
          <h4>How to produce</h4>
          <p>Develop your idea and record it</p>
          <p>To record you will need: Sound, Camera, Light and a Computer</p>
        </section>
        <section id="delivery">
          <h4>How to delivery</h4>
          <p>You can choose any digital media like: YouTube, Twich or Instagram.</p>
        </section>
      </div>
    </div>
  </main>
</div>
        <script>
           const $ = document.querySelector.bind(document);

function TabNavigation() {
  const html = {
    links: [...$('.tab-links').children],
    contents: [...$('.tab-content').children],
    openTab: $('[data-default]'),
  }
  
  function hideAllTabContent(){
    html.contents.forEach(section => {
      section.style.display = "none";
    });
  }
  
  function removeAllActiveClas(){
    html.links.forEach(tab => {
      tab.className = tab.className.replace(' active', '');
    })
  }
  
  function showCurrentTab(id){    
    const tabcontent = $('#'+ id);
    tabcontent.style.display = 'block';
  }
  
  function selecttab(event) {
    hideAllTabContent();
    removeAllActiveClas();
    
    const target = event.currentTarget;
    showCurrentTab(target.dataset.id);
    
    target.className += ' active';
  }
  
  function listenForChange(){
    html.links.forEach(tab => {
      tab.addEventListener('click', selecttab);
    });
  }
  
  function init(){
    hideAllTabContent();
    listenForChange();
    
    html.openTab.click();
  }
  
  return {
    init
  }
}

window.addEventListener('load', () => {
  const tabNavigation = TabNavigation();
  tabNavigation.init();
})
</script>
    </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Grading Essay</title>
        <link rel="stylesheet" href="./style/createEvaluatorEssay.css">
        <link rel="stylesheet" href="./style/criteria.css">
        <!--     Fonts and icons     -->
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700"/>
        <!-- Nucleo Icons -->
        <link href="./assets/css/nucleo-icons.css" rel="stylesheet" />
        <link href="./assets/css/nucleo-svg.css" rel="stylesheet" />
        <!-- Font Awesome Icons -->
        <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
        <!-- Material Icons -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet"/>
        <!-- CSS Files -->
        <link id="pagestyle" href="./assets/css/material-dashboard.css?v=3.0.0" rel="stylesheet"/>
    </head>
    <body>
        <!--        <aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3 bg-gradient-dark"id="sidenav-main" >
              <div class="sidenav-header">
                <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
                <a class="navbar-brand m-0" href="./" target="_blank">
                  <img src="./assets/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo"/>
                  <span class="ms-1 font-weight-bold text-white">Graded</span>
                </a>
              </div>
              <hr class="horizontal light mt-0 mb-2" />
        <%@include file="evaluator/sidebarEvaluator.jsp" %>
        </aside>-->

        <div class="panel panel-default section-contents-prompt">
            <header class="panel-heading">
                <div class="panel-heading-main">
                    <h3> ${evaluatorEssay.writerEssay.essay.title}</h3>
                    <div class="panel-heading-actions">
                        <!--               <a class="start-over saved"><b>Level: </b><i class="icon-start-again"></i></a>
                                <a class="save saved" id="save"><i class="icon-save"></i><span>  ${evaluatorEssay.writerEssay.essay.level.levelName}</span></a></br>
                                <a class="start-over saved"><b>Type: </b><i class="icon-start-again"></i></a>
                                <a class="save saved" id="save"><i class="icon-save"></i><span>  ${evaluatorEssay.writerEssay.essay.type.typeName}</span></a>-->
                    </div>
                </div>
                <div class="panel-heading-utility"></div>
            </header>
            <main class="panel-body">
                <div class="content">
                    <div>
                        <p>Description: ${evaluatorEssay.writerEssay.essay.description}</p>
                    </div>
                </div>
            </main>
            <div class="section-contents-footer">
                <!--    <i>Do not write your real name and surname or email address in your answer.</i>-->
            </div>
            <!--      <c:set var="evaluatorEssayID" value="${evaluatorEssayID}" />-->
            <div class="section-input-field">
                <main class="panel-body">
                    <textarea maxlength="9999" placeholder="Write your text here." data-question-id="7e3b5830-dd8e-11e6-9c3d-0600f3293cb7" rows="10" id="script-text" spellcheck="false" data-listener-added_f7595f09="true"  readonly> ${evaluatorEssay.writerEssay.contentEssay}</textarea>
                </main>
            </div>
            <div class="respond start-writing">
                <!--    <span>(the word count for this task is about 30 words).</span>-->
            </div>
            <div class="section-input-bottom"></div>
            <div>
            </div>
        </div>
        <div class="wrapper">

            <h2 style="margin-top: 20px">Rate and review this article.</h2>

            <input type="hidden" name="evaluatorEssayID" value="${evaluatorEssay.evaluatorEssayID}">
            <div class="rating">

                <label class="form-label">Grade: </label> ${evaluatorEssay.score}
            </div>
            <label class="rating" class="form-label">Feedback:</label>
            ${evaluatorEssay.feedbackContent}

            <input type="hidden" name="writerEssayId" value="${evaluatorEssay.writerEssay.writerEsssayId}">

            <div class="btn-group">



            </div>

        </div>
        <div id="page">
            <header>
                <h2>Feeback Essay Criteria</h2>
                <c:if test="${mess != null}">
                    <div class=" alert-danger text-success" role="alert">
                        <h3>${mess}</h1>
                    </div>
                </c:if>
            </header>
            <main style="text-align: center">
                <div id="tabs">
                    <div class="tab-links">
                        <c:forEach var="criteriaFeedback" items="${criteriaFeedbackList}">
                            <button data-id="criteria${criteriaFeedback.criteriaFeedbackID}" data-default>${criteriaFeedback.criteria.criteriaName}</button>
                        </c:forEach>
                    </div>

                    <div class="tab-content">
                        <c:forEach var="criteriaFeedback" items="${criteriaFeedbackList}">
                            <section id="criteria${criteriaFeedback.criteriaFeedbackID}"  data-default>
                                <h4>${criteriaFeedback.criteria.criteriaName}</h4>
                                <p>${criteriaFeedback.criteria.description}</p>
                                <h5>  Feedback Detail for Criteria: ${criteriaFeedback.feedbackContent}<h3>




                                        </section>
                                    </c:forEach>
                                    </div>
                                    </div>
                                    </main>
                                    </div>
                                    <script>
                                        function goBack() {
                                            window.history.back();
                                        }
                                    </script>
                                    <script>
                                        const $ = document.querySelector.bind(document);

                                        function TabNavigation() {
                                            const html = {
                                                links: [...$('.tab-links').children],
                                                contents: [...$('.tab-content').children],
                                                openTab: $('[data-default]'),
                                            }

                                            function hideAllTabContent() {
                                                html.contents.forEach(section => {
                                                    section.style.display = "none";
                                                });
                                            }

                                            function removeAllActiveClas() {
                                                html.links.forEach(tab => {
                                                    tab.className = tab.className.replace(' active', '');
                                                })
                                            }

                                            function showCurrentTab(id) {
                                                const tabcontent = $('#' + id);
                                                tabcontent.style.display = 'block';
                                            }

                                            function selecttab(event) {
                                                hideAllTabContent();
                                                removeAllActiveClas();

                                                const target = event.currentTarget;
                                                showCurrentTab(target.dataset.id);

                                                target.className += ' active';
                                            }

                                            function listenForChange() {
                                                html.links.forEach(tab => {
                                                    tab.addEventListener('click', selecttab);
                                                });
                                            }

                                            function init() {
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
                                    <!--   Core JS Files   -->
                                    <script src="./assets/js/core/popper.min.js"></script>
                                    <script src="./assets/js/core/bootstrap.min.js"></script>
                                    <script src="./assets/js/plugins/perfect-scrollbar.min.js"></script>
                                    <script src="./assets/js/plugins/smooth-scrollbar.min.js"></script>
                                    <script>
                                                var win = navigator.platform.indexOf("Win") > -1;
                                                if (win && document.querySelector("#sidenav-scrollbar")) {
                                                    var options = {
                                                        damping: "0.5",
                                                    };
                                                    Scrollbar.init(document.querySelector("#sidenav-scrollbar"), options);
                                                }
                                    </script>
                                    <!-- Github buttons -->
                                    <script async defer src="https://buttons.github.io/buttons.js"></script>
                                    <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
                                    <script src="./assets/js/material-dashboard.min.js?v=3.0.0"></script>
                                    </body>
                                    </html>

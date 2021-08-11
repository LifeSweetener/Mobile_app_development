![#c5f015](https://via.placeholder.com/15/ffd700/000000?text=+) <b>`ЧЕТВЁРТАЯ ПРАКТИКА`</b>  
![#f03c15](https://via.placeholder.com/15/f03c15/000000?text="+") `Прикреплённые изображения в директориях — это скриншоты с моего телефона с ОС Android 5.1.1 (Lollipop)`
# Mobile_app_development
The repository inculdes results of university mobile apps works

## ОПИСАНИЕ
<p>В каталоге лежит проект <b>"practice4"<b>, в котором содержится пять модулей, плюс, в этом каталоге находится отдельный проект с выполненным завершающим эту практику контрольным заданием (шестым по счёту).</p>
  <h3>Список модулей</h3>
  <p><ul>
  <li><p><b>app</b> — это модуль, где решено самое первое задание практики — <b>"Thread"</b>.</p></li>
  <li><p><b>data_thread</b> — второе задание четвёртой практики, в котором нас знакомят с тремя Android-методами, позволяющими главному потоку приложения спокойно перерисовать UI-объект View без плохих последствий: <i>runOnUiThread()</i>, <i>post()</i> и <i>postDelayed()</i>. С их помощью дополнительные потоки, которые мы сами создаём, могут передать сообщение главному потоку, чтобы тот выполнил какую-нибудь задачу, часто связанную с визуальным изменением GUI.</p>
    <p>В программе сначала запустится метод runn1, потом runn2, а последним выполнится - runn3, которые перерисуют текстовую метку интерфейса.</p>
    <p>Используя один главный поток приложения "UI thread", мы не сможем одновременно вычислить что-то, а потом как результат вычислений перерисовать элемент View, потому что у этого главного потока слишком мало времени на это - ему нужно делать ещё кучу задач. У UI-потока просто кончится время на перерисовку элемента пользовательского интерфейса. <p>Поэтому были придуманы методы runOnUiThread(), post() и postDelayed(), чтобы разделить вычисления и перерисовку View. Вычисления производит второстепенный поток, который мы создаём, а перерисовку - главный поток, чем он и должен заниматься. А это означает, что времени на перерисовку элемента у главного UI-потока будет предостаточно, и не произойдёт никакой ошибки в приложении.</p>
    <p>Метод runOnUiThread() полезен при перерисовке какого-то одного View: мы создаём свой второстепенный поток, в нём делаем вычислительные операции и в нём же вызываем этот метод, чтобы передать операцию перерисовки конкретного View главному потоку.
<p>Методы post() и postDelayed() полезны, когда у нас имеется множество разных View, и нам нужно все их перерисовать одновременно. Главный поток по очереди, друг за другом перерисовывает их, пока выполняются вычисления в нашем дополнительном потоке.</p>
    <p>Таким образом, наши дополнительные потоки передают сообщения главному UI-потоку, чтобы он выполнил визуальное редактирование элементов View, то есть так потоки могут общаться между собой.</p>
  </li>
  <li><p><b>loadermanager</b> — </p>
  </li>
  </ul></p>

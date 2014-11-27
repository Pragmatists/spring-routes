var app = route();

app.get('/ask_bean_by_name', function (req, resp) {
    var mrBean = bean('mrBean');
    resp.send(mrBean.say());
});
app.get('/ask_bean_by_class', function (req, resp) {
    var mrBean = bean(org.springroutes.MrBean.class);
    resp.send(mrBean.say());

});

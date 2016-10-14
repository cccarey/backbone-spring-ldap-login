define([
    'jquery',
    'lib/underscore',
    'lib/backbone',
    'lib/handlebars',
    'config',
    'models/loader',
    'views/header',
    'text!../../templates/login.html'
], function($, _, Backbone, Handlebars, config, models, header, loginTemplate) {
    'use strict';

    return Backbone.View.extend({
        id: "login_view",
        template: Handlebars.compile(loginTemplate),

        events: {
            "click #login": "submitLogin"
        },

        initialize: function(args) {
            args.pageInfo.set("pageTitle", "Login");
            args.pageInfo.unset("menuItems");
        },

        render: function() {
            $(this.el).html(this.template());
        },

        submitLogin: function() {
            alert('not implemented');
            return;
        }
    });
});

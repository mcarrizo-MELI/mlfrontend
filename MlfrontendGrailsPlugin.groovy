class MlfrontendGrailsPlugin {
    // the plugin version
    def version = "0.7.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.7 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp",
			"grails-app/views/html/index.gsp"
    ]

    // TODO Fill in these fields
    def author = "Pablo Moretti / Pablo Duranti"
    def authorEmail = "pablo.moretti@mercadolibre.com / pablo.duranti@mercadolibre.com "
    def title = "MercadoLibre Frontend"
    def description = ""

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/mlfrontend"

    def doWithWebDescriptor = { xml ->
        def contextParam = xml.'context-param'
		contextParam[contextParam.size() - 1] + {
		   'filter'{
			   'filter-name'('MlFilter')
			   'filter-class'('org.springframework.web.filter.DelegatingFilterProxy')
			   'init-param' {
				   'param-name'('targetBeanName')
				   'param-value'('mlRequestParamsFilter')
			   }	   
			   'init-param' {
                   'param-name'('targetFilterLifecycle')
                   'param-value'('true')
		       }
		   }   
		}
		   
		def filters = xml.'filter'
		filters[filters.size() - 1] + {
		  'filter-mapping'{
			'filter-name'('MlFilter')
			'url-pattern'('/*')
			'dispatcher'('REQUEST')
			'dispatcher'('FORWARD')
		  }
		}
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}

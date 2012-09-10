package mmc



import org.junit.*
import grails.test.mixin.*

@TestFor(EndpointController)
@Mock(Endpoint)
class EndpointControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/endpoint/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.endpointInstanceList.size() == 0
        assert model.endpointInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.endpointInstance != null
    }

    void testSave() {
        controller.save()

        assert model.endpointInstance != null
        assert view == '/endpoint/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/endpoint/show/1'
        assert controller.flash.message != null
        assert Endpoint.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/endpoint/list'


        populateValidParams(params)
        def endpoint = new Endpoint(params)

        assert endpoint.save() != null

        params.id = endpoint.id

        def model = controller.show()

        assert model.endpointInstance == endpoint
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/endpoint/list'


        populateValidParams(params)
        def endpoint = new Endpoint(params)

        assert endpoint.save() != null

        params.id = endpoint.id

        def model = controller.edit()

        assert model.endpointInstance == endpoint
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/endpoint/list'

        response.reset()


        populateValidParams(params)
        def endpoint = new Endpoint(params)

        assert endpoint.save() != null

        // test invalid parameters in update
        params.id = endpoint.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/endpoint/edit"
        assert model.endpointInstance != null

        endpoint.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/endpoint/show/$endpoint.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        endpoint.clearErrors()

        populateValidParams(params)
        params.id = endpoint.id
        params.version = -1
        controller.update()

        assert view == "/endpoint/edit"
        assert model.endpointInstance != null
        assert model.endpointInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/endpoint/list'

        response.reset()

        populateValidParams(params)
        def endpoint = new Endpoint(params)

        assert endpoint.save() != null
        assert Endpoint.count() == 1

        params.id = endpoint.id

        controller.delete()

        assert Endpoint.count() == 0
        assert Endpoint.get(endpoint.id) == null
        assert response.redirectedUrl == '/endpoint/list'
    }
}

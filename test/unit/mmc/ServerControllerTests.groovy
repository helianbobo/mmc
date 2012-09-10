package mmc



import org.junit.*
import grails.test.mixin.*

@TestFor(ServerController)
@Mock(Server)
class ServerControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/server/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.serverInstanceList.size() == 0
        assert model.serverInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.serverInstance != null
    }

    void testSave() {
        controller.save()

        assert model.serverInstance != null
        assert view == '/server/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/server/show/1'
        assert controller.flash.message != null
        assert Server.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/server/list'


        populateValidParams(params)
        def server = new Server(params)

        assert server.save() != null

        params.id = server.id

        def model = controller.show()

        assert model.serverInstance == server
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/server/list'


        populateValidParams(params)
        def server = new Server(params)

        assert server.save() != null

        params.id = server.id

        def model = controller.edit()

        assert model.serverInstance == server
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/server/list'

        response.reset()


        populateValidParams(params)
        def server = new Server(params)

        assert server.save() != null

        // test invalid parameters in update
        params.id = server.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/server/edit"
        assert model.serverInstance != null

        server.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/server/show/$server.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        server.clearErrors()

        populateValidParams(params)
        params.id = server.id
        params.version = -1
        controller.update()

        assert view == "/server/edit"
        assert model.serverInstance != null
        assert model.serverInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/server/list'

        response.reset()

        populateValidParams(params)
        def server = new Server(params)

        assert server.save() != null
        assert Server.count() == 1

        params.id = server.id

        controller.delete()

        assert Server.count() == 0
        assert Server.get(server.id) == null
        assert response.redirectedUrl == '/server/list'
    }
}

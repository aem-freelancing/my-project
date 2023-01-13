def buildQuery(page, term) {
    def queryManager = session.workspace.queryManager;
    def statement = 'select * from nt:base where jcr:path like \'' + page.path + '/%\' and sling:resourceType = \'' + term + '\'';
    /*Here term is the sling:resourceType property value*/
    queryManager.createQuery(statement, 'sql');
}

/*Defined Content Hierarchy */
final def page = getPage('/content/my-project')
/*Template which is searched in the content hierarchy */
final def query = buildQuery(page, 'my-project/components/inner');
final def result = query.execute()

println 'No Of pages found = ' + result.nodes.size();

result.nodes.each { node ->
    println "node path = ${node.path}"

    def copyPath = node.path + '_copy'
    def newPath = node.parent.path + '/inner-wrapper/' + node.name
    def parentResourceType = node.parent.get('sling:resourceType');

    if (parentResourceType != 'my-project/components/wrapper') {
        session.workspace.copy(node.path, copyPath)
        node.rename('inner-wrapper')
        node.set('sling:resourceType', 'my-project/components/wrapper')
        session.save();

        session.workspace.move(copyPath, newPath)
        session.save();

        def currentPage = pageManager.getContainingPage(node.path);
        def map = currentPage.getContentResource().getValueMap();
        if (map.containsKey('cq:lastReplicated')) {
            println "node wrapper added = ${node.path}"
        } else {
            activate(node.path)
        }
    }
}
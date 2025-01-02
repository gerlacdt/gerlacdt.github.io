.PHONY: wiki
wiki:
	tiddlywiki wiki --listen


.PHONY: publish
publish:
	tiddlywiki wiki --build index && mv output/index.html wiki/

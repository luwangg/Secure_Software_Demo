deploy:
	git subtree push --prefix software heroku master

force:
	git push heroku `git subtree split --prefix software master`:master --force
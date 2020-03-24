### Instuctions

In order to write a blog post you need to start the tiddlywiki with
`./start.sh`. Then you can work inside the browser on http://localhost:8080

After you finished your work you should close the browser window and
stop the started tiddlywiki with `CTRL-c` in the terminal. Next you
can build the production release with `./deploy.sh`. This script will
collect all changes an packages everything together for a final
release.

The last step to publish the new blogpost is to push to the master
branch in github. Afterwards our changes are visible under http://gerlacdt.github.io

``` bash
# start tiddlywikki locally and start writing your articles and/or blog posts
./start.sh

# package everything to a release candidate
./deploy.sh

# release you newes changes
git push origin master
```

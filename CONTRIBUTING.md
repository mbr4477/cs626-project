# Contributing

## Getting Started
1. Download IntelliJ IDEA
2. Create a new project from version control
3. Sign in with your GitHub account
4. Select the `cs626-project` repository

## Adding a Feature
1. Create a new branch for your contribution. In IntelliJ IDEA, you can do this by clicking the "Git: " text at the bottom right of the window and selecting "New branch". A best practice is to use the naming pattern `<initials>-<feature name>`, such as `mbr-add-mapper`.
2. Work on your new local branch
3. Commit changes to the local branch, then push to GitHub
4. Your new branch will appear in GitHub. If your feature code (e.g., initial stable mapper) is ready, create a pull request to merge your branch code into the master branch. On GitHub click "Pull requests" and then "New pull request" and configure the request to merge your new branch into master (make sure it is not master into your branch).
5. We will review the pull request and add to master if everything looks okay.
6. Once merged, you can delete your feature branch and create another one for the next feature you will work on.

## Miscellaneous
- Frequently do a pull to merge the latest changes from GitHub into your local copy of the repository
- Make sure to commit your changes with a message before pushing to GitHub
- You will not be able to run the code on your computer (unless you have Hadoop installed). You will need to transfer the file to a Linux VM with Hadoop to test on data. On your local machine, the JAR should be built and placed in `build/libs/` folder. See the Cloudera Word Count demo or https://mruss.dev/hadoop-ubuntu-vm/hadoop_ubuntu_vm/ for examples.
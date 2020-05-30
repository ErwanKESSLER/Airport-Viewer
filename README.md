PPII GROUPE 5
Erwan KESSLER <Redacted> <Redacted>


For C code everything will go in Code/C/src the cmake being at v3.5 it will help will the build process.
For JAVA code everything will go in CODE/JAVA/src and the gradle build system will be used for all the task (v5.2.1).

For GDP everything will go in GDP/ either a CR or a report.

How to clone:
You cd into a directory where you can store stuff then enters:
`git clone https://gitlab.telecomnancy.univ-lorraine.fr/ppii-2k19/project-grp05.git`

Now lets configure your stuff first do `git config --global user.name <your name>` `git config --global user.email <surname.name@telecomancy.eu> ` if not already done

If you use SSH key just skip that part:

- then do `git config --global credentials.helper <wincred|cache|store>` (cache will cache it for the session so till you close the terminal, wincred will use the windows credentials gui and store will store them on your hard drive: Not recommended)

- try to do a `git pull` and if it doesnt ask you for your email then password do `git remote set-url origin https://gitlab.telecomnancy.univ-lorraine.fr/ppii-2k19/project-grp05.git`


How to Commit:

Please select your branch first so : `git checkout <your name>`(to see available branches do `git branch`).

Your branch may be behind what you have on the gitlab so do `git pull` then `git rebase origin/master` (to have everything up to date with master) or `git rebase origin/dev` (to be with dev).

Do your stuff and commit only files accordingly with `git add <file|files|*|.>` (dont commit stuff related to your IDE or file not as sources: add everything to the corresponding .gitignore if necessary).  

Do `git status` and verify your stuff before committing.

Once done do `git commit -m <meaningful message>`.

Then do `git push origin <your name>` to push your branch to the gitlab.

---

In case you want to publish it for the other you need to push to the dev staging branch so you need to do first `git checkout Dev`.

Then do `git merge <your name>` and once the merge is done (you may need to resolve conflicts) please do `git push origin Dev`.

Once on the dev staging, tests will run (yours and the others) if all tests pass and you have the approval to push to master then you can start a merge request to merge into `master`

At this point there will be a review and if it is agreed then change will be pushed.

AT ALL TIME MASTER SHOULD BE FUNCTIONAL!

To do a merge request you need to go on the gitlab page and go in your repository/branch and click on the green button.
